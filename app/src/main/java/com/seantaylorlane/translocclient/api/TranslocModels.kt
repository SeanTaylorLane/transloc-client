package com.seantaylorlane.translocclient.api

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

object TranslocModels {

    data class AgenciesResponse(val data: List<Agency>) {
        @Entity
        data class Agency(@SerializedName("agency_id") @PrimaryKey val id: String,
                          @SerializedName("long_name") val name: String)
    }

}