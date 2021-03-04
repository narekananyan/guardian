package am.codeschool.theguardianapi.ui.section

import am.codeschool.theguardianapi.MainActivity
import am.codeschool.theguardianapi.MyApplication
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.codeschool.theguardianapi.databinding.SectionFragmentBinding
import am.codeschool.theguardianapi.injection.Injection
import am.codeschool.theguardianapi.model.data.pojo.Section
import am.codeschool.theguardianapi.ui.adapters.SectionAdapter
import am.codeschool.theguardianapi.ui.base.BaseFragment
import am.codeschool.theguardianapi.ui.content.ContentFragment
import android.util.Log

class SectionFragment : BaseFragment(),
    SectionAdapter.OnSectionItemClickListener,
    MainActivity.OnSearchViewChangeListener,
    SectionAdapter.OnSectionFavoriteClickListener {
    private lateinit var sectionFragmentBinding: SectionFragmentBinding
    private val sectionFactory: SectionFactory by  lazy { SectionFactory(Injection.getSearchRepo(),(requireActivity().application as MyApplication).sectionRepo)}
    private val viewModel: SectionViewModel by lazy { ViewModelProvider(this,sectionFactory).get(SectionViewModel::class.java) }
    private lateinit var adapter:SectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSections()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        sectionFragmentBinding = SectionFragmentBinding.inflate(inflater,container,false)
        return sectionFragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
        viewModel._getRoomSection.observe(viewLifecycleOwner,{
                adapter.favChanged(it)
        })
    }

    override fun initView() {
        adapter = SectionAdapter()
        adapter.setOnSectionItemClickListener(this)
        adapter.setOnSectionFavClickListener(this)
        sectionFragmentBinding.sectionRecycler.adapter = adapter
        (requireActivity() as MainActivity).addOnSearchViewChangeListener(this)
    }

    override fun observeLiveData(){
        viewModel._getSectionData.observe(viewLifecycleOwner,{
            adapter.update(it)
            hideLoader()
        })
    }
    companion object {
        fun newInstance() = SectionFragment()
    }

    override fun callSectionClick(url: String) {
        val fr = ContentFragment.newInstance()
        val bundle =Bundle()
        bundle.putString("sectionurl",url)
        fr.arguments = bundle
        addFrBackStack(fr)
    }

    override fun textChange(newText: String) {
        Log.d("query","viewmodellsection")
        viewModel.sectionFilter(newText)
    }

    override fun onClick(item: Section.Response.Result,position: Int,isChecked:Boolean) {
        viewModel.updateRoomData(item,position,isChecked)
    }

}