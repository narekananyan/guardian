package am.codeschool.theguardianapi.ui.section

import am.codeschool.theguardianapi.model.repository.SearchRepository
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SectionFactory(private val searchRepository: SearchRepository,private val roomRepository: SectionRoomRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SectionViewModel::class.java)) {
            return SectionViewModel(searchRepository,roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}