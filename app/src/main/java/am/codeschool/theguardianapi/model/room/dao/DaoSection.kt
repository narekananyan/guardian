package am.codeschool.theguardianapi.model.room.dao

import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoSection {
    @Query("DELETE FROM section_table WHERE section_table.id=:id")
    fun deleteById(id:String)
    @Query("SELECT * FROM section_table ORDER BY webTitle ASC")
    fun getFavoriteSections():LiveData<List<SectionRoom>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sectionRoom: SectionRoom)
    @Query("DELETE FROM section_table")
     fun deleteAll()
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(sectionRoom: SectionRoom)
}