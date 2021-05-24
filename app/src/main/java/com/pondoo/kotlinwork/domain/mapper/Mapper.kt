package com.pondoo.kotlinwork.domain.mapper

interface Mapper<R, M>{
    fun mapFrom(response: R) : M
}