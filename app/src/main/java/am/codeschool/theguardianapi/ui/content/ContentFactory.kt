package am.codeschool.theguardianapi.ui.content

import am.codeschool.theguardianapi.model.repository.SearchRepository
import am.codeschool.theguardianapi.model.room.repo.ContentRoomRepo
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContentFactory(private val searchRepository: SearchRepository,private val roomRepository: ContentRoomRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentViewModel::class.java)) {
            return ContentViewModel(searchRepository,roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}