package am.codeschool.theguardianapi.model.room.repo

import am.codeschool.theguardianapi.model.room.dao.DaoContent
import am.codeschool.theguardianapi.model.room.dao.DaoSection
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val contentDao: DaoContent, private val sectionDao: DaoSection) {
    val allSectionRooms:LiveData<List<SectionRoom>> = sectionDao.getFavoriteSections()
    fun insertSection(sectionRoom: SectionRoom) {
        sectionDao.insert(sectionRoom)
    }
    fun update(sectionRoom: SectionRoom){
        sectionDao.update(sectionRoom)
    }
    fun sectionDeleteById(id:String){
        sectionDao.deleteById(id)
    }
    ///Content
    fun insertContent(contentRoom: ContentRoom) {
        contentDao.insert(contentRoom)
    }
//    val allContentRooms:List<ContentRoom> = contentDao.getFavoriteContents()
    fun contentDeleteById(id:String){
        contentDao.deleteById(id)
    }
    fun deleteAllFavorites(){
        sectionDao.deleteAll()
        contentDao.deleteAll()
    }
}