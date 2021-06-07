package com.intive.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uid"),
        onDelete = ForeignKey.CASCADE
    )]
)
class Event(
    @ColumnInfo(name = "uid")
    var uid: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "importanceLevel")
    var importanceLevel: Int,
) {

    @PrimaryKey(autoGenerate = true)
    var eid = 0
}
