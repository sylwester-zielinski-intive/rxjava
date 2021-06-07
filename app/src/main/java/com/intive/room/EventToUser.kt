package com.intive.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uid"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Event::class,
        parentColumns = arrayOf("eid"),
        childColumns = arrayOf("eid"),
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["uid", "eid"]
)
class EventToUser {

    @ColumnInfo(name = "uid")
    var uid: Int = 0

    @ColumnInfo(name = "eid")
    var eid: Int = 0
}
