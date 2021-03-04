package am.codeschool.theguardianapi.model.room.repo

import am.codeschool.theguardianapi.model.room.dao.DaoContent
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import androidx.lifecycle.LiveData

class ContentRoomRepo(private val contentDao: DaoContent) {
    fun insertContent(contentRoom: ContentRoom) {
        contentDao.insert(contentRoom)
    }
    val allContentRooms:LiveData<List<ContentRoom>> = contentDao.getFavoriteContents()
    fun contentDeleteById(id:String){
        contentDao.deleteById(id)
    }
}