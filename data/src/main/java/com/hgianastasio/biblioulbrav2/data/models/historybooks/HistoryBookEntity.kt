package com.hgianastasio.biblioulbrav2.data.models.historybooks

import com.google.gson.annotations.SerializedName

/**
 * Created by heitorgianastasio on 4/24/17.
 */
class HistoryBookEntity {
    var id: Long = 0

    @SerializedName("titulo")
    var title: String? = null

    @SerializedName("agendado")
    var deadline: String? = null

    @SerializedName("devolvido")
    var devolution: String? = null

    @SerializedName("biblioteca")
    var library: String? = null

}