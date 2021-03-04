package am.codeschool.theguardianapi.model.room.repo

import am.codeschool.theguardianapi.model.room.dao.DaoSection
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class SectionRoomRepo(private val sectionDao: DaoSection) {
    val allSectionRooms:LiveData<List<SectionRoom>> = sectionDao.getFavoriteSections()
    fun insertSection(sectionRoom: SectionRoom) {
        sectionDao.insert(sectionRoom)
    }
    fun update(sectionRoom: SectionRoom){
        sectionDao.update(sectionRoom)
    }
    fun deleteAll(){
        sectionDao.deleteAll()
    }
    fun sectionDeleteById(id:String){
        sectionDao.deleteById(id)
    }
}