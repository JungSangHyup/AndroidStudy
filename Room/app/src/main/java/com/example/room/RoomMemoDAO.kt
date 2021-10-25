package com.example.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface RoomMemoDAO {
    @Query("select * from orm_memo")
    fun getAll(): List<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}