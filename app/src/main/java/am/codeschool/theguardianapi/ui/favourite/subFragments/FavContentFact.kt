package am.codeschool.theguardianapi.ui.favourite.subFragments

import am.codeschool.theguardianapi.model.room.repo.ContentRoomRepo
import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavContentFact(private val contentRepo: ContentRoomRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(FavoriteItemViewModel::class.java)){
        return FavoriteItemViewModel(contentRepo) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
}
}