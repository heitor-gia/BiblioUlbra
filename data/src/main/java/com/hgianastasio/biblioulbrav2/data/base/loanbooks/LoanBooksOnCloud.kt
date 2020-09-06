package com.hgianastasio.biblioulbrav2.data.base.loanbooks

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity
import java.io.IOException

/**
 * Created by heitorgianastasio on 4/29/17.
 */
interface LoanBooksOnCloud {
    @Throws(IOException::class)
    fun get(cgu: String?, listener: OnResultListener<List<LoanBookEntity>>)
}