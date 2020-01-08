package ru.gonchar17narod.goncharoom.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.gonchar17narod.goncharoom.business.Entity

@Dao
interface EntityDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedEntities(): LiveData<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: Entity)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}