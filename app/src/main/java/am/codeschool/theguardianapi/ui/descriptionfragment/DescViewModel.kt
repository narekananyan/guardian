package am.codeschool.theguardianapi.ui.descriptionfragment

import am.codeschool.theguardianapi.model.data.ApiCallBack
import am.codeschool.theguardianapi.model.data.pojo.Description
import am.codeschool.theguardianapi.model.repository.SearchRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DescViewModel(private val repository: SearchRepository):ViewModel() {
    private val getSectionData = MutableLiveData<Description?>()
    val _getSectionData: LiveData<Description?>
        get()=getSectionData
    fun loadPathData(url: String) {
        repository.loadPathData(url, object : ApiCallBack<Description> {
            override fun onSuccess(response: Description?) {
                getSectionData.value = response
            }

        })
    }
}