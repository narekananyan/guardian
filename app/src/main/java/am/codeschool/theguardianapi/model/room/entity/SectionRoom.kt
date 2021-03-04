package am.codeschool.theguardianapi.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section_table")
data class SectionRoom(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val webTitle: String
)
