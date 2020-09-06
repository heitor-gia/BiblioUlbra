package com.hgianastasio.biblioulbrav2.data.models.search.searchmodel

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchModelEntity {
    var search: String? = null
    var cookie: String? = null
    var base: String? = null
    var page = 0
    var isExact: String? = null
    var field: String? = null
    var media: String? = null
    var language: String? = null

    constructor() {}
    constructor(search: String?) {
        this.search = search
    }

    constructor(cookie: String?, page: Int) {
        this.cookie = cookie
        this.page = page
    }

}