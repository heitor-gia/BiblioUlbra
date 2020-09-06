package com.hgianastasio.biblioulbrav2.core.base.boundaries

import java.util.*

/**
 * Created by heitorgianastasio on 4/24/17.
 */
abstract class Mapper<I, O> {
    abstract fun transform(input: I): O?
    fun trasform(input: List<I>): List<O> {
        val result: MutableList<O> = LinkedList()
        for (inputObj in input) transform(inputObj)?.let(result::add)
        return result
    }

    abstract fun transformBack(input: O): I?
    fun tansformBack(input: List<O>): List<I> {
        val result: MutableList<I> = LinkedList()
        for (inputObj in input) transformBack(inputObj)?.let(result::add)
        return result
    }
}