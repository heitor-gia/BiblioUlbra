package com.hgianastasio.biblioulbrav2.data.models.historybooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import java.text.ParseException
import java.text.SimpleDateFormat
/**
 * Created by heitorgianastasio on 4/25/17.
 */
class HistoryBookEntityMapper constructor() : Mapper<HistoryBook, HistoryBookEntity>() {
    override fun transform(input: HistoryBook): HistoryBookEntity {
        val output = HistoryBookEntity()
        output.title = input!!.title
        output.id = input.id
        output.deadline = SimpleDateFormat("dd/MM/yyyy").format(input.deadline)
        output.devolution = SimpleDateFormat("dd/MM/yyyy").format(input.devolution)
        output.library = input.library
        return output
    }

    override fun transformBack(input: HistoryBookEntity): HistoryBook {
        val output = HistoryBook()
        return try {
            output.title = input!!.title
            output.id = input.id
            output.deadline = SimpleDateFormat("dd/MM/yyyy").parse(input.deadline)
            output.devolution = SimpleDateFormat("dd/MM/yyyy").parse(input.devolution)
            output.library = input.library
            output
        } catch (e: ParseException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
}