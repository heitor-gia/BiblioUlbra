package com.hgianastasio.biblioulbrav2.models.search.searchmodel

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchModelModelMapper constructor() : Mapper<SearchModel, SearchModelModel>() {
    override fun transform(input: SearchModel): SearchModelModel {
        val output = SearchModelModel()
        output.base = input.base!!.id
        output.cookie = input.cookie
        output.search = input.search
        output.isExact = input.isExact
        output.field = input.field!!.id
        output.media = input.media!!.id
        output.language = input.language!!.id
        output.page = input.page
        return output
    }

    override fun transformBack(input: SearchModelModel): SearchModel {
        val output = SearchModel()
        output.search = input.search
        output.page = input.page
        output.cookie = input.cookie
        output.isExact = input.isExact
        output.base = SearchBase.findById(input.base)
        output.field = SearchField.findById(input.field)
        output.media = SearchMedia.findById(input.media)
        output.language = SearchLanguage.findById(input.language)
        return output
    }
}