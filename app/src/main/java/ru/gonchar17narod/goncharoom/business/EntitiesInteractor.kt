package ru.gonchar17narod.goncharoom.business

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.gonchar17narod.goncharoom.data.database.EntityRoomDatabase
import ru.gonchar17narod.goncharoom.data.repository.EntityRepository

object EntitiesInteractor {

    private var repository: EntityRepository? = null

    fun getEntities(
        application: Application,
        scope: CoroutineScope
    ) =
        getRepository(
            application,
            scope
        )
            .allEntities

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(
        application: Application,
        scope: CoroutineScope,
        entity: Entity
    ) =
        scope.launch {
        getRepository(
            application,
            scope
        )
            .insert(entity)
    }

    private fun getRepository(
        application: Application,
        scope: CoroutineScope
    ) =
        repository ?: with(
        EntityRepository(
            EntityRoomDatabase.getDatabase(
                application,
                scope
            )
                .entityDao()
        )
    ) {
        repository = this
        this
    }
}