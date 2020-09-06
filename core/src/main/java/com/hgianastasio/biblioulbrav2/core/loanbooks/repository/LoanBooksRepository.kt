package com.hgianastasio.biblioulbrav2.core.loanbooks.repository

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook
import java.io.IOException

/**
 * Created by heitorgianastasio on 4/24/17.
 */
interface LoanBooksRepository {
    @Throws(IOException::class)
    fun get(cgu: String?, onResultListener: OnResultListener<List<LoanBook>>)

    @Throws(IOException::class)
    fun reload(cgu: String?, onResultListener: OnResultListener<List<LoanBook>>)
}