package com.example.redballtoy.findyourpet.common.data.api.model.mapper

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}