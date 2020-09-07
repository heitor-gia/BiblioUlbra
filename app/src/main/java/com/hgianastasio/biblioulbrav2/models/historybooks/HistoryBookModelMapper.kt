package com.hgianastasio.biblioulbrav2.models.historybooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook
import java.text.SimpleDateFormat
/**
 * Created by heitor_12 on 03/05/17.
 */
class HistoryBookModelMapper constructor() : Mapper<HistoryBook, HistoryBookModel>() {
    override fun transform(input: HistoryBook): HistoryBookModel {
        val output = HistoryBookModel()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        output.title = input.title
        output.id = input.id
        output.library = input.library
        output.deadline = dateFormat.format(input.deadline)
        output.devolution = dateFormat.format(input.devolution)
        return output
    }

    override fun transformBack(input: HistoryBookModel): HistoryBook? {
        val output = HistoryBook()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return try {
            output.title = input.title
            output.id = input.id
            output.library = input.library
            output.deadline = dateFormat.parse(input.deadline)
            output.devolution = dateFormat.parse(input.devolution)
            output
        } catch (ex: Exception) {
            null
        }
    }
}