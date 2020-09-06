package com.hgianastasio.biblioulbrav2.data.models.loanbooks

import com.google.gson.annotations.SerializedName

/**
 * Created by heitorgianastasio on 4/24/17.
 */
class LoanBookEntity {
    var id: Long = 0

    @SerializedName("titulo")
    var title: String? = null

    @SerializedName("agendado")
    var deadline: String? = null

    @SerializedName("taxa")
    var penalty: String? = null

    @SerializedName("biblioteca")
    var library: String? = null

    @SerializedName("chamada")
    var code: String? = null

    @SerializedName("descricao")
    var description: String? = null

}