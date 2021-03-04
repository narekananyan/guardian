package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import am.codeschool.theguardianapi.ui.roomviewmodel.RoomBaseViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavSectionFact(private val sectionRoomRepo: SectionRoomRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoriteSectionViewModel::class.java)){
            return FavoriteSectionViewModel(sectionRoomRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}