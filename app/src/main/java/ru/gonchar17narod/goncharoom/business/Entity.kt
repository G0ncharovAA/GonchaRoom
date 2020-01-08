package ru.gonchar17narod.goncharoom.business

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Entity(@PrimaryKey @ColumnInfo(name = "word") val label: String)