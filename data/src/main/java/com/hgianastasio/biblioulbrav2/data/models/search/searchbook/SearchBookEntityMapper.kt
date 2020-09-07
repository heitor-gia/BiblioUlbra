package com.hgianastasio.biblioulbrav2.data.models.search.searchbook

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook
import java.text.ParseException
import java.text.SimpleDateFormat
/**
 * Created by heitor_12 on 09/05/17.
 */
class SearchBookEntityMapper constructor() : Mapper<SearchBook, SearchBookEntity>() {
    override fun transform(input: SearchBook): SearchBookEntity? {
        throw IllegalStateException("Not implemented")
    }

    override fun transformBack(input: SearchBookEntity): SearchBook? {
        val output = SearchBook()
        output.author = input!!.author
        output.catchedExps = input.catchedExps?.split("(?<=\\))".toRegex())?.toTypedArray()
        output.number = input.number!!.toLong()
        output.title = input.title
        try {
            output.year = SimpleDateFormat("yyyy").parse(input.year)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return output
    }
}