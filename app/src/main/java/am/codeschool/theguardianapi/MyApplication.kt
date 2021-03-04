package am.codeschool.theguardianapi

import am.codeschool.theguardianapi.model.room.databace.ApplicationDataBase
import am.codeschool.theguardianapi.model.room.repo.ContentRoomRepo
import am.codeschool.theguardianapi.model.room.repo.RoomRepository
import am.codeschool.theguardianapi.model.room.repo.SectionRoomRepo
import android.app.Application

class MyApplication: Application() {
    private val database by lazy { ApplicationDataBase.getDatabase(this) }
    val repository by lazy { RoomRepository(database.contentDao(),database.sectionDao()) }
    val sectionRepo by lazy { SectionRoomRepo(database.sectionDao()) }
    val contentRepo by lazy { ContentRoomRepo(database.contentDao()) }
}