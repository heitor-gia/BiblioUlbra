package com.hgianastasio.biblioulbrav2.models.user

/**
 * Created by heitor_12 on 02/09/16.
 */
class UserModel {
    var name: String? = null
    var cgu: String? = null
    var loans = 0
    var history = 0
    var bookings = 0
    var debt: String? = null
    var isOverdue = false

    val nameLastName: String
        get() {
            val names = name!!.split(" ".toRegex()).toTypedArray()
            return names[0] + " " + names[names.size - 1]
        }
}