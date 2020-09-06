package com.hgianastasio.biblioulbrav2.core.loanbooks

import java.util.*

/**
 * Created by heitorgianastasio on 4/24/17.
 */
class LoanBook {
    var id: Long = 0
    var title: String? = null
    var deadline: Date? = null
    var penalty = 0.0
    var library: String? = null
    var code: String? = null
    var description: String? = null

}