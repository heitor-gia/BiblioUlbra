package com.hgianastasio.biblioulbrav2.data.models.search.searchbook

import com.google.gson.annotations.SerializedName

/**
 * Created by heitorgianastasio on 4/24/17.
 */
class SearchBookEntity {
    @SerializedName("titulo")
    var title: String? = null

    @SerializedName("numero")
    var number: String? = null

    @SerializedName("autor")
    var author: String? = null

    @SerializedName("ano")
    var year: String? = null

    @SerializedName("exsemprestados")
    var catchedExps: String? = null

}