package com.example.redballtoy.findyourpet.common.domain.model.mapper

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}