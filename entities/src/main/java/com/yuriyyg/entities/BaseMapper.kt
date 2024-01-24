package com.yuriyyg.entities

interface BaseMapper<Input, Output> {
    fun map (input: Input) : Output
}