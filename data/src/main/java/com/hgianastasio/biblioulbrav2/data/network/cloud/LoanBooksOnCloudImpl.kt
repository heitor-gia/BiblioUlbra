package com.hgianastasio.biblioulbrav2.data.network.cloud

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCloud
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi
import java.io.IOException
/**
 * Created by heitorgianastasio on 4/29/17.
 */
class LoanBooksOnCloudImpl constructor(var api: BiblioUlbraApi) : LoanBooksOnCloud {
    @Throws(IOException::class)
    override fun get(cgu: String?, listener: OnResultListener<List<LoanBookEntity>>) {
        listener(api.getLoansFromUser(cgu).execute().body() ?: emptyList())
    }

}