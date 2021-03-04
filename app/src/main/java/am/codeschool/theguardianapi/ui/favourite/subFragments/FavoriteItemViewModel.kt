package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.repo.ContentRoomRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteItemViewModel(private val contentRoomRepo: ContentRoomRepo) : ViewModel() {
    val liveData = contentRoomRepo.allContentRooms
    fun deleteContentById(id:String){
        contentRoomRepo.contentDeleteById(id)
    }
}