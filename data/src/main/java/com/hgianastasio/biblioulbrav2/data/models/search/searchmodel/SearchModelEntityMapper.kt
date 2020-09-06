package com.hgianastasio.biblioulbrav2.data.models.search.searchmodel

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchBase
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchField
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchLanguage
import com.hgianastasio.biblioulbrav2.core.search.constants.SearchMedia
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
class SearchModelEntityMapper @Inject constructor() : Mapper<SearchModel?, SearchModelEntity?>() {
    override fun transform(input: SearchModel?): SearchModelEntity {
        val output = SearchModelEntity()
        output.isExact = if (input!!.isExact) "Y" else "N"
        output.cookie = input.cookie
        output.search = input.search
        output.page = input.page
        output.base = if (input.base != null) input.base!!.code else ""
        output.media = if (input.media != null) input.media!!.code else ""
        output.field = if (input.field != null) input.field!!.code else ""
        output.language = if (input.field != null) input.language!!.code else ""
        return output
    }

    override fun transformBack(input: SearchModelEntity?): SearchModel {
        val output = SearchModel()
        output.base = SearchBase.findByCode(input!!.base)
        output.media = SearchMedia.findByCode(input.media)
        output.field = SearchField.findByCode(input.field)
        output.language = SearchLanguage.findByCode(input.language)
        output.isExact = input.isExact.equals("y", ignoreCase = true)
        output.cookie = input.cookie
        output.search = input.search
        output.page = input.page
        return output
    }
}