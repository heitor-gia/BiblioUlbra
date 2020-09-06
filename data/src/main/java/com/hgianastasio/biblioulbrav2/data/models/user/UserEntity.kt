package com.hgianastasio.biblioulbrav2.data.models.user

import com.google.gson.annotations.SerializedName

/**
 * Created by heitor_12 on 02/09/16.
 */
class UserEntity {
    @SerializedName("usuario")
    var name: String? = null

    @SerializedName("cgu")
    var cgu: String? = null

    @SerializedName("emprestimos")
    var loans: String? = null

    @SerializedName("historico")
    var history: String? = null

    @SerializedName("reservas")
    var bookings: String? = null

    @SerializedName("caixa")
    var debt: String? = null

}