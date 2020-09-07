package com.hgianastasio.biblioulbrav2.models.search.searchbook

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook
import java.text.SimpleDateFormat
/**
 * Created by heitor_12 on 31/05/17.
 */
class SearchBookModelMapper constructor() : Mapper<SearchBook, SearchBookModel>() {
    override fun transform(input: SearchBook): SearchBookModel {
        val output = SearchBookModel()
        output.author = input.author
        output.title = input.title
        output.catchedExps = input.catchedExps
        output.number = input.number
        try {
            output.year = SimpleDateFormat("yyyy").format(input.year)
        } catch (e: Exception) {
            output.year = "Não informado"
        }
        return output
    }

    override fun transformBack(input: SearchBookModel): SearchBook {
        throw IllegalStateException("Not implemented.")
    }
}