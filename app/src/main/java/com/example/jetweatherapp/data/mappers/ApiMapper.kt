package com.example.jetweatherapp.data.mappers

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiEntity: Entity): Domain
}