package com.yuriyyg.domain.mapper

interface BaseMapper<Input, Output> {
    fun map (input: Input) : Output
}