package am.codeschool.theguardianapi.ui.favourite

import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import am.codeschool.theguardianapi.ui.roomviewmodel.RoomBaseViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavFragFactory(private val roomRepository: RoomRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavouritViewModel::class.java)){
            return FavouritViewModel(roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}