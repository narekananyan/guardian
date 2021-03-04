package am.codeschool.theguardianapi.ui.content

import am.codeschool.theguardianapi.MainActivity
import am.codeschool.theguardianapi.MyApplication
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.databinding.ContentFragmentBinding
import am.codeschool.theguardianapi.injection.Injection
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.ui.adapters.ContentAdapter
import am.codeschool.theguardianapi.ui.base.BaseFragment
import am.codeschool.theguardianapi.ui.descriptionfragment.DescriptionFragment
import am.codeschool.theguardianapi.ui.filterdialog.FilterFrDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView

class ContentFragment : BaseFragment(),
    ContentAdapter.OnContentItemClickListener,
    MainActivity.OnSearchListener,
    FilterFrDialog.OnSearchStartListener,
    ContentAdapter.OnContentFavoriteClickListener
    {
    private lateinit var contentFragment: ContentFragmentBinding
    private val contentFactory: ContentFactory by  lazy { ContentFactory(Injection.getSearchRepo(),(requireActivity().application as MyApplication).contentRepo) }
    private val viewModel: ContentViewModel by lazy { ViewModelProvider(this,contentFactory).get(ContentViewModel::class.java)}
    private lateinit var adapter:ContentAdapter
    private lateinit var recContent:RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle ==null){
            viewModel.loadNewestData()
        }else 
        bundle.getString("sectionurl")?.let { viewModel.loadSectionItem(it) }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentFragment = ContentFragmentBinding.inflate(inflater,container,false)
        return contentFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
    }
    override fun initView() {
        adapter = ContentAdapter()
        adapter.setOnItemClickListener(this)
        recContent = contentFragment.recContent
        recContent.adapter = adapter
        (requireActivity() as MainActivity).addSearchListener(this)
        adapter.setOnSectionFavClickListener(this)
    }

    override fun observeLiveData() {
        viewModel._roomData.observe(viewLifecycleOwner,{
            adapter.favChanged(it)
        })
        viewModel._getContentData.observe(viewLifecycleOwner,{
            adapter.update(it)
            hideLoader()
        })
    }

    companion object {
        fun newInstance() = ContentFragment()
    }

    override fun searchStart() {
        val frManager = requireActivity().supportFragmentManager.beginTransaction()
        val filterFrDialog = FilterFrDialog.instance()
        filterFrDialog.addSearchStartListener(this)
        filterFrDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFragmentTheme)
        filterFrDialog.show(frManager, "FR")
    }

    override fun callForDescription(url: String) {
        val trans = requireActivity().supportFragmentManager.beginTransaction()
        val descriptionFragment = DescriptionFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        descriptionFragment.arguments = bundle
        trans.add(R.id.frameConteiner, descriptionFragment, "desc")
        trans.addToBackStack(null)
        trans.commit()
        showLoader()
    }

    override fun searchStart(listQuery: MutableMap<String, String>) {
        viewModel.loadDataFromFilter(listQuery)
    }

        override fun onClick(item: Content.Response.Result, position: Int, isChecked: Boolean) {
            viewModel.updateRoomData(item,position,isChecked)
        }


    }