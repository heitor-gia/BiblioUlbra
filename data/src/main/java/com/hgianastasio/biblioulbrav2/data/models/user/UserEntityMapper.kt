package com.hgianastasio.biblioulbrav2.data.models.user

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.user.models.User
/**
 * Created by heitorgianastasio on 4/26/17.
 */
class UserEntityMapper constructor() : Mapper<User?, UserEntity?>() {
    override fun transformBack(input: UserEntity?): User {
        val output = User()
        output.name = input!!.name
        output.cgu = input.cgu
        output.bookings = input.bookings!!.toInt()
        output.history = input.history!!.toInt()
        output.loans = input.loans!!.toInt()
        output.debt = input.debt!!.toDouble()
        return output
    }

    override fun transform(input: User?): UserEntity {
        val output = UserEntity()
        output.name = input!!.name
        output.cgu = input.cgu
        output.bookings = input.bookings.toString()
        output.history = input.history.toString()
        output.loans = input.loans.toString()
        output.debt = input.debt.toString()
        return output
    }
}