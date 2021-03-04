package am.codeschool.theguardianapi.ui.roomviewmodel

import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomFactory(private val roomRepository: RoomRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomBaseViewModel::class.java)){
            return RoomBaseViewModel(roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}