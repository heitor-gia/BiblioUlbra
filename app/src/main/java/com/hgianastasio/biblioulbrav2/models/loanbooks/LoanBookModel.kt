package com.hgianastasio.biblioulbrav2.models.loanbooks

/**
 * Created by heitorgianastasio on 4/24/17.
 */
class LoanBookModel {
    var id: Long = 0
    var title: String? = null
    var deadline: String? = null
    var penalty: String? = null
    var library: String? = null
    var code: String? = null
    var description: String? = null
    var isOverdue = false
    private var justRenewed = false
    var isSingleCopy = false

    fun wasJustRenewed(): Boolean {
        return justRenewed
    }

    fun setJustRenewed(justRenewed: Boolean) {
        this.justRenewed = justRenewed
    }

}