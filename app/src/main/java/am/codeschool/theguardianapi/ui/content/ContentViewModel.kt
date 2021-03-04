package am.codeschool.theguardianapi.ui.content

import am.codeschool.theguardianapi.model.data.ApiCallBack
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.repository.SearchRepository
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.repo.ContentRoomRepo
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentViewModel(
    private val repository: SearchRepository,
    private val roomRepository: ContentRoomRepo
) : ViewModel() {
    private val getContentData = MutableLiveData<List<Content.Response.Result>?>()
    val _getContentData: LiveData<List<Content.Response.Result>?>
        get() = getContentData

    private var reserveContentData = mutableListOf<Content.Response.Result>()
    private var roomData:LiveData<List<ContentRoom>> = MutableLiveData()
    val _roomData:LiveData<List<ContentRoom>>
    get() = roomData
    init {
       roomData= roomRepository.allContentRooms
    }
    fun loadSectionItem(url: String) {
        repository.callSectionItem(url, object : ApiCallBack<Content> {
            override fun onSuccess(response: Content?) {
                if (response != null) {
                    reserveContentData.clear()
                    reserveContentData.addAll(response.response.results)
                    checkFavorite()
                }
            }

        })

    }

    fun loadNewestData() {
        repository.searchContent(object : ApiCallBack<Content> {
            override fun onSuccess(response: Content?) {
                if (response != null) {
                    reserveContentData.clear()
                    reserveContentData.addAll(response.response.results)
                    checkFavorite()
                }
            }

            override fun onFailure(error: String?) {
                Log.d("loadData", "Failure")
            }

        })
    }

    fun loadDataFromFilter(mutableMap: MutableMap<String, String>) {
        repository.searchFromFilter(mutableMap, object : ApiCallBack<Content> {
            override fun onSuccess(response: Content?) {
                if (response != null) {
                    reserveContentData.clear()
                    reserveContentData.addAll(response.response.results)
                    checkFavorite()
                }
            }
        })
    }

    fun checkFavorite() {
        reserveContentData.forEach { result ->
            roomData.value?.forEach {
                if (result.id == it.id)
                    result.favorite = true
            }
        }
        getContentData.value = reserveContentData
    }

    fun updateRoomData(item: Content.Response.Result, position: Int, isChecked: Boolean) {
        reserveContentData[position].favorite = isChecked
        if (isChecked) {
            roomRepository.insertContent(
                ContentRoom(
                    item.id,
                    item.webTitle,
                    item.sectionName,
                    item.fields.thumbnail
                )
            )
        } else {
            roomRepository.contentDeleteById(item.id)
        }

    }
}