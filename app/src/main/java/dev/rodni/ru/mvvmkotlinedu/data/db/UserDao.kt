package dev.rodni.ru.mvvmkotlinedu.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.CURRENT_USER_ID
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.User

//data access object
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upser(user: User) : Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}