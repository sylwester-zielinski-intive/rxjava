package com.intive.room

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user")
    val all: List<User?>?

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray?): List<User?>?

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String?, last: String?): User?

    @Insert
    fun insertUsers(vararg users: User?)

    @Insert
    fun insertEvents(vararg events: Event?)

    @Delete
    fun delete(user: User?)

    @Query("SELECT e.title, u.first_name, u.last_name FROM event e LEFT JOIN user u ON e.uid = u.uid")
    fun queryEventsWithUsers(): List<EventWithUser>

    @Query("SELECT e.eid, u.first_name, u.last_name FROM eventtouser e LEFT JOIN user u ON e.uid = u.uid LEFT JOIN event ev ON e.eid = ev.eid")
    fun queryEventsWithUsers2(): List<EventWithUser>
}

class EventWithUser(

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    var lastName: String? = null
)
