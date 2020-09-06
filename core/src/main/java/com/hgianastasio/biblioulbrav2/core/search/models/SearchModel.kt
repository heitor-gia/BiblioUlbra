package com.hgianastasio.biblioulbrav2.core.search.models

import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia

/**
 * Created by heitor_12 on 23/11/16.
 */
class SearchModel {
    var search: String? = null
    var cookie: String? = null
    var base: SearchBase? = null
    var page = 0
    var isExact = false
    var language: SearchLanguage? = null
    var field: SearchField? = null
    var media: SearchMedia? = null

    constructor() {}
    constructor(search: String?) {
        this.search = search
    }

    constructor(cookie: String?, page: Int) {
        this.cookie = cookie
        this.page = page
    }

}