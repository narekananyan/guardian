package am.codeschool.theguardianapi.model.room.dao

import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface DaoContent {
    @Query("SELECT * FROM content_table ORDER BY webTitle ASC")
    fun getFavoriteContents(): LiveData<List<ContentRoom>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contentRoom: ContentRoom)
    @Query("DELETE FROM content_table")
    fun deleteAll()
    @Query("DELETE FROM content_table WHERE content_table.id=:id")
    fun deleteById(id:String)
    @Query("SELECT count(*) FROM content_table WHERE content_table.id = :id")
    fun checkContent(id:String):Int
}