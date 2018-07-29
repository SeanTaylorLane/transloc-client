package com.seantaylorlane.translocclient.api

import com.google.gson.annotations.SerializedName

object TranslocModels {

    data class AgenciesResponse(val data: List<Agency>) {
        data class Agency(@SerializedName("long_name") val name: String,
                          @SerializedName("agency_id") val id: String)
    }

}