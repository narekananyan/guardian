package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.MyApplication
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.codeschool.theguardianapi.databinding.FavoriteSectionFragmentBinding
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import am.codeschool.theguardianapi.ui.adapters.subadapters.FavSectionAdapter
import am.codeschool.theguardianapi.ui.roomviewmodel.RoomBaseViewModel
import am.codeschool.theguardianapi.ui.roomviewmodel.RoomFactory
import android.util.Log

class FavoriteSectionFr : Fragment(),FavSectionAdapter.OnDeleteButtonClick {
    private lateinit var favBinding: FavoriteSectionFragmentBinding
    private val sectionViewModel: FavoriteSectionViewModel by lazy { ViewModelProvider(this,
        FavSectionFact((requireActivity().application as MyApplication).sectionRepo)
    ).get(FavoriteSectionViewModel::class.java)}
    private lateinit var adapter: FavSectionAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sectionViewModel.getSections()
        favBinding = FavoriteSectionFragmentBinding.inflate(inflater,container,false)
        return favBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavSectionAdapter()
        favBinding.favSecRecycler.adapter = adapter
        adapter.setDeleteClickListener(this)
        sectionViewModel.liveData.observe(viewLifecycleOwner,{
            Log.d("FavSect","Observe Section1")
            if (it != null) {
                Log.d("FavSect","Observe Section")
                adapter.updateList(it)
            }
        })

    }
    companion object {
        fun newInstance() = FavoriteSectionFr()
    }

    override fun onClick(item: SectionRoom) {
        sectionViewModel.deleteSectionByID(item.id)
    }

}