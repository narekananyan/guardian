package am.codeschool.theguardianapi

import am.codeschool.theguardianapi.databinding.ActivityMainBinding
import am.codeschool.theguardianapi.ui.adapters.FragmentAdapter
import am.codeschool.theguardianapi.ui.base.BaseActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var adapter: FragmentAdapter
    private lateinit var mainViewPager: ViewPager2
    private lateinit var listner: OnSearchViewChangeListener
    private lateinit var onSearchListener:OnSearchListener
    private lateinit var onDeleteAllClick:DeleteAllClickLitenere

    interface DeleteAllClickLitenere {
        fun deleteAllFavorites()
    }

    interface OnSearchListener{
        fun searchStart()
    }

    interface OnSearchViewChangeListener {
        fun textChange(newText: String)
    }

    fun addDeleteListener(onDeleteAllClick:DeleteAllClickLitenere){
        this.onDeleteAllClick = onDeleteAllClick
    }
    fun addOnSearchViewChangeListener(listener: OnSearchViewChangeListener) {
        this.listner = listener
    }
    fun addSearchListener(listener: OnSearchListener) {
        this.onSearchListener = listener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initViews()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                hideMenuItems()
                showMenu(position)
            }
        })
        val search = menu.findItem(R.id.searchSection)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search Sections"
        Log.d("query", "menu")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("query", "Main2")
                if (newText != null) {
                    Log.d("query", "main")
                    listner.textChange(newText)
                }
                return true
            }

        })
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                searchView.onActionViewCollapsed()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Log.d("query", "onoptionselected")
        return when (item.itemId) {

            R.id.filterContent -> {
                onSearchListener.searchStart()
                true
            }
            R.id.deleteAll -> {
                onDeleteAllClick.deleteAllFavorites()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun initViews() {
        setSupportActionBar(mainBinding.toolbar)
        val frNames = resources.getStringArray(R.array.frag_names)
        val icons = arrayOf(
            R.drawable.ic_baseline_section,
            R.drawable.ic_baseline_content,
            R.drawable.ic_baseline_favourite
        )
        mainViewPager = mainBinding.mainViewPager
        adapter = FragmentAdapter(this)
        mainViewPager.adapter = adapter
        TabLayoutMediator(mainBinding.tabLayout, mainViewPager) { tab, position ->
            tab.text = frNames[position]
            tab.setIcon(icons[position])
        }.attach()
        showLoader()

    }

    override fun showLoader() {
        mainBinding.loader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        mainBinding.loader.visibility = View.GONE
    }

    private fun showMenu(id: Int) {
        mainBinding.toolbar.menu
        mainBinding.toolbar.menu.getItem(id).isVisible = true
    }

    private fun hideMenuItems() {
        val size = mainBinding.toolbar.menu.size
        for (id in 0 until size) {
            mainBinding.toolbar.menu.getItem(id).isVisible = false
        }
    }
//    fun timer() {
//        val cdt = object : CountDownTimer(2000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//
//            }
//
//            override fun onFinish() {
//                mainBinding.splashFr.visibility = View.GONE
//            }
//        }.start()
//    }
}