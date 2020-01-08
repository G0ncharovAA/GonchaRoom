package ru.gonchar17narod.goncharoom.data.repository

import androidx.lifecycle.LiveData
import ru.gonchar17narod.goncharoom.business.Entity
import ru.gonchar17narod.goncharoom.data.database.EntityDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class EntityRepository(private val entityDao: EntityDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allEntities: LiveData<List<Entity>> = entityDao.getAlphabetizedEntities()

    suspend fun insert(entity: Entity) {
        entityDao.insert(entity)
    }
}