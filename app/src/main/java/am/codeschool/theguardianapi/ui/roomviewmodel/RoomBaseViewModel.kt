package am.codeschool.theguardianapi.ui.roomviewmodel

import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class RoomBaseViewModel(private val roomRepository: RoomRepository): ViewModel() {

    fun deleteAll(){
        roomRepository.deleteAllFavorites()
    }
}