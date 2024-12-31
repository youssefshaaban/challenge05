package com.banquemisr.domain

interface DataMapper<T,R> {
    fun execute(data:T):R
}