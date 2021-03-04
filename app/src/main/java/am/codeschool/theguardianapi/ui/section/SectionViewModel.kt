package am.codeschool.theguardianapi.ui.section

import am.codeschool.theguardianapi.model.repository.SearchRepository
import am.codeschool.theguardianapi.model.data.ApiCallBack
import am.codeschool.theguardianapi.model.data.pojo.Section
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import android.util.Log
import androidx.lifecycle.*
import java.util.*
import kotlin.collections.ArrayList

class SectionViewModel(
    private val repository: SearchRepository,
    private val roomRepository: SectionRoomRepo
) : ViewModel() {
    private var getSectionData = MutableLiveData<List<Section.Response.Result>>()
    val _getSectionData: LiveData<List<Section.Response.Result>?>
        get() = getSectionData
    private var reserveSectionData = mutableListOf<Section.Response.Result>()
    private var roomList: LiveData<List<SectionRoom>> = MutableLiveData()
//     var roomReserve :LiveData<List<Section.Response.Result>> = MutableLiveData()
    val _getRoomSection:LiveData<List<SectionRoom>>
    get() = roomList
    init {
        roomList = roomRepository.allSectionRooms
    }
     val mediatorLiveData = MediatorLiveData<List<Section.Response.Result>>()

    init {

//        roomReserve = Transformations.switchMap(roomRepository.allSectionRooms){
//            checkFavorite(it)
//        }
//        mediatorLiveData.addSource(roomReserve){
//            mediatorLiveData.value=it
//        }
//        mediatorLiveData.addSource(_getSectionData){
//            mediatorLiveData.value=it
//        }
    }

    fun checkFavorite(fav:List<SectionRoom>):LiveData<List<Section.Response.Result>> {
        Log.d("quekjjhjry","TSSSSSSSSSSSSSSSSS")
        val sections: MutableLiveData<List<Section.Response.Result>> = MutableLiveData()
        reserveSectionData.forEach { result ->
            fav.forEach {
                if (result.id == it.id)
                    result.favorite = true
            }
        }
        sections.value = reserveSectionData
        return sections
    }
    fun loadSections() {
        repository.callSection(object : ApiCallBack<Section> {
            override fun onSuccess(response: Section?) {
                if (response != null) {
                    reserveSectionData.clear()
                    reserveSectionData.addAll(response.response.results)
                    checkFavorite()

                }
            }

        })
    }

    fun sectionFilter(newText: String) {
        val filteredList =
            reserveSectionData.filter { it.id.contains(newText.toLowerCase(Locale.ROOT)) }
        Log.d("query", "viewmodell")
        getSectionData.value = filteredList
    }


    fun updateRoomData(item: Section.Response.Result,position: Int, isChecked: Boolean){
        reserveSectionData[position].favorite=isChecked
        if (isChecked){
            roomRepository.insertSection(SectionRoom(item.id,item.webTitle))

        }else if(!isChecked){
            roomRepository.sectionDeleteById(item.id)
        }

    }
    fun deleteAll(){
        roomRepository.deleteAll()
    }
    fun checkFavorite() {
        reserveSectionData.forEach { result ->
            roomList.value?.forEach {
                if (result.id == it.id)
                    result.favorite = true
            }
        }
        getSectionData.value = reserveSectionData
    }
}