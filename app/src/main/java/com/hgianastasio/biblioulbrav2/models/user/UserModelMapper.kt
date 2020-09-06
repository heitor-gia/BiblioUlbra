package com.hgianastasio.biblioulbrav2.models.user

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.user.models.User
import javax.inject.Inject

/**
 * Created by heitor_12 on 03/05/17.
 */
class UserModelMapper @Inject constructor() : Mapper<User, UserModel>() {
    override fun transform(input: User): UserModel {
        val output = UserModel()
        output.name = input.name?.formatName()
        output.cgu = input.cgu
        output.bookings = input.bookings
        output.history = input.history
        output.loans = input.loans
        output.debt = String.format("%.2f", input.debt)
        output.isOverdue = input.debt < 0
        return output
    }

    override fun transformBack(input: UserModel): User {
        val output = User()
        output.name = input.name
        output.cgu = input.cgu
        output.bookings = input.bookings
        output.history = input.history
        output.loans = input.loans
        output.debt = input.debt!!.toDouble()
        return output
    }

    private fun String.formatName(): String {
        val words = split(" ".toRegex()).toTypedArray()
        var result = ""
        for (word in words) {
            val piece = if (word.equals("da", ignoreCase = true) ||
                    word.equals("de", ignoreCase = true) ||
                    word.equals("do", ignoreCase = true) ||
                    word.equals("dos", ignoreCase = true) ||
                    word.equals("das", ignoreCase = true)) {
                word.toLowerCase()
            } else {
                word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()
            }
            result += " $piece"
        }
        return result.trim()
    }
}