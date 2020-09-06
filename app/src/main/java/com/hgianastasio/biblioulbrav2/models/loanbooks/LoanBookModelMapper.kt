package com.hgianastasio.biblioulbrav2.models.loanbooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 10/05/17.
 */
class LoanBookModelMapper @Inject constructor() : Mapper<LoanBook, LoanBookModel>() {
    override fun transform(input: LoanBook): LoanBookModel {
        val output = LoanBookModel()
        output.code = input.code
        output.title = input.title
        output.deadline = SimpleDateFormat("dd/MM/yyyy").format(input.deadline)
        output.description = input.description
        output.id = input.id
        output.library = input.library
        output.penalty = String.format("%.2f", input.penalty)
        output.isOverdue = input.deadline!!.before(Date(System.currentTimeMillis()))
        output.isSingleCopy = input.code!!.contains("ex.1")
        output.setJustRenewed(input.deadline!!.after(Date(getJustRenewedTreshold(output))))
        return output
    }

    override fun transformBack(input: LoanBookModel): LoanBook {
        throw IllegalStateException("Not implemented")
    }

    private fun getJustRenewedTreshold(model: LoanBookModel): Long {
        return if (model.isSingleCopy) System.currentTimeMillis() + ONE_DAY_IN_MILLISECONDS else System.currentTimeMillis() + SIX_DAYS_IN_MILLISECONDS
    }

    companion object {
        private const val SIX_DAYS_IN_MILLISECONDS: Long = 518400000
        private const val ONE_DAY_IN_MILLISECONDS: Long = 86400000
    }
}