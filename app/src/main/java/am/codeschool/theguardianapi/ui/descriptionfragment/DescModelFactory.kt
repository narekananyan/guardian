package am.codeschool.theguardianapi.ui.descriptionfragment

import am.codeschool.theguardianapi.model.repository.SearchRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DescModelFactory(private val repository: SearchRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DescViewModel::class.java)) {
            return DescViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}