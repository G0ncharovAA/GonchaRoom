package ru.gonchar17narod.goncharoom.view.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ru.gonchar17narod.goncharoom.business.EntitiesInteractor
import ru.gonchar17narod.goncharoom.business.Entity

// Class extends AndroidViewModel and requires application as a parameter.
class EntityViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData gives us updated words when they change.
    val allEntities: LiveData<List<Entity>>

    init {
        allEntities = EntitiesInteractor.getEntities(
            getApplication(),
            viewModelScope
        )
    }

    fun insert(entity: Entity) =
        EntitiesInteractor.insert(
            getApplication(),
            viewModelScope,
            entity
        )
}