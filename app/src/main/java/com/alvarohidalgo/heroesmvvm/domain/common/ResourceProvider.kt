package com.alvarohidalgo.heroesmvvm.domain.common

interface ResourceProvider {
    fun getString(stringId: Int): String
}