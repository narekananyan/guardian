package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteSectionViewModel(private val secRepo:SectionRoomRepo) : ViewModel() {
    private var getFavSection = MutableLiveData<List<SectionRoom>>()
    val _getFavSectionRoom: LiveData<List<SectionRoom>?>
        get()=getFavSection
    fun getSections(){
        getFavSection.value = secRepo.allSectionRooms.value
    }
    val liveData = secRepo.allSectionRooms
    fun deleteSectionByID(id:String){
        secRepo.sectionDeleteById(id)
    }
}