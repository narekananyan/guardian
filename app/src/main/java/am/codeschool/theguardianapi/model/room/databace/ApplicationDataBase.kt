package am.codeschool.theguardianapi.model.room.databace
import am.codeschool.theguardianapi.model.room.dao.DaoContent
import am.codeschool.theguardianapi.model.room.dao.DaoSection
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SectionRoom::class, ContentRoom::class], version = 1, exportSchema = false)
 abstract class ApplicationDataBase:RoomDatabase() {
    abstract fun contentDao(): DaoContent
    abstract fun sectionDao(): DaoSection
    companion object{
        private var INSTANCE: ApplicationDataBase?=null
        fun getDatabase(context: Context): ApplicationDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDataBase::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                Log.d("Database","Creat")
                instance
            }
        }
    }
}