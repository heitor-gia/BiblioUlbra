package com.hgianastasio.biblioulbrav2.models.search.searchmodel

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchModelModel {
    var search: String? = null
    var cookie: String? = null
    var base = 0
    var page = 0
    var isExact = false
    var language = 0
    var field = 0
    var media = 0

    constructor() {}
    constructor(search: String?) {
        this.search = search
    }

    constructor(cookie: String?, page: Int) {
        this.cookie = cookie
        this.page = page
    }

}