package com.example.cubosteste.data.mapper

interface Mapper<in M, out E> {

    fun mapFromRemote(type: M): E

    fun mapFromRemoteForInitial(type: M): E

}