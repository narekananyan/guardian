package am.codeschool.theguardianapi.ui.favourite

import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import androidx.lifecycle.ViewModel

class FavouritViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    fun deleteAll(){
        roomRepository.deleteAllFavorites()
    }
}