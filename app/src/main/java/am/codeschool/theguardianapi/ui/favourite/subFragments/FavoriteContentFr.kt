package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.MyApplication
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.codeschool.theguardianapi.databinding.FavoriteItemFragmentBinding
import am.codeschool.theguardianapi.ui.adapters.subadapters.FavContentAdapter

class FavoriteContentFr : Fragment(), FavContentAdapter.OnDeleteButtonClick,FavContentAdapter.OnItemClickListener {
    lateinit var favBinding: FavoriteItemFragmentBinding
    lateinit var adapter: FavContentAdapter
    private val viewModel: FavoriteItemViewModel by lazy { ViewModelProvider(this,
        FavContentFact((requireActivity().application as MyApplication).contentRepo)
    ).get(FavoriteItemViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavContentAdapter()
        observeLiveData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favBinding = FavoriteItemFragmentBinding.inflate(inflater,container,false)
        return favBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favBinding.favContRecycler.adapter = adapter
        adapter.setDeleteClickListener(this)
    }
    private fun observeLiveData(){
        viewModel.liveData.observe(this,{
            adapter.updateList(it)
        })
    }
    companion object {
        fun newInstance() = FavoriteContentFr()
    }

    override fun onClick(itemId: String) {
        viewModel.deleteContentById(itemId)
    }

    override fun onItemClick(id: String) {
        TODO("Not yet implemented")
    }

}