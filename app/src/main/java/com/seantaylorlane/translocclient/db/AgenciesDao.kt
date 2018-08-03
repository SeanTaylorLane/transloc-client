package com.seantaylorlane.translocclient.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.seantaylorlane.translocclient.api.TranslocModels

@Dao
interface AgenciesDao {
    @Insert(onConflict = REPLACE)
    fun save(agencies: List<TranslocModels.AgenciesResponse.Agency>)

    @Update
    fun update(agency: TranslocModels.AgenciesResponse.Agency)

    @Query("SELECT * FROM Agency WHERE id = :id")
    fun load(id: Int): LiveData<TranslocModels.AgenciesResponse.Agency>

    @Query("SELECT * FROM Agency")
    fun loadAll(): LiveData<List<TranslocModels.AgenciesResponse.Agency>>
}