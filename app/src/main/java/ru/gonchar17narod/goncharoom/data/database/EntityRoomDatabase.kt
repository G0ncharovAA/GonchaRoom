package ru.gonchar17narod.goncharoom.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.gonchar17narod.goncharoom.business.Entity

// Annotates class to be a Room Database with a table (entity) of the Entity class
@Database(entities = arrayOf(Entity::class), version = 1, exportSchema = false)
public abstract class EntityRoomDatabase : RoomDatabase() {

    abstract fun entityDao(): EntityDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EntityRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EntityRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EntityRoomDatabase::class.java,
                        "entities_database"
                    )
                        .addCallback(
                            EntitiesDatabaseCallback(scope)
                        )
                        .build()
                    INSTANCE = instance
                    instance
                }
        }
    }

    private class EntitiesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    with(database.entityDao()) {
                        insert(Entity("Should"))
                        insert(Entity("store"))
                        insert(Entity("data"))
                    }
                }
            }
        }
    }
}