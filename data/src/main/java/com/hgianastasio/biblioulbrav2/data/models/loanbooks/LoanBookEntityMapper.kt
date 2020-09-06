package com.hgianastasio.biblioulbrav2.data.models.loanbooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import java.text.SimpleDateFormat
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/29/17.
 */
class LoanBookEntityMapper @Inject constructor() : Mapper<LoanBook, LoanBookEntity>() {
    override fun transform(input: LoanBook): LoanBookEntity {
        val output = LoanBookEntity()
        output.library = input!!.library
        output.title = input.title
        output.code = input.code
        output.deadline = SimpleDateFormat("dd/MM/yyyy").format(input.deadline)
        output.penalty = String.format("%.2f", input.penalty)
        output.description = input.description
        output.id = input.id
        return output
    }

    override fun transformBack(input: LoanBookEntity): LoanBook? {
        val output = LoanBook()
        return try {
            output.id = input!!.id
            output.code = input.code
            output.title = input.title
            output.library = input.library
            output.description = input.description
            output.deadline = SimpleDateFormat("dd/MM/yyyy").parse(input.deadline)
            output.penalty = if (input.penalty?.isEmpty() == true) 0.0 else input.penalty!!.toDouble()
            output
        } catch (e: Exception) {
            null
        }
    }
}