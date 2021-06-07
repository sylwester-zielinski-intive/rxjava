package com.intive.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    var lastName: String? = null
) {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

}
