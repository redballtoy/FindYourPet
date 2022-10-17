package com.example.redballtoy.findyourpet.common.data.api.model.mappers

import com.example.redballtoy.findyourpet.common.data.api.model.ApiPagination
import com.example.redballtoy.findyourpet.common.domain.model.pagination.Pagination
import javax.inject.Inject

class ApiPaginationMapper @Inject constructor():ApiMapper<ApiPagination?,Pagination> {
    override fun mapToDomain(apiEntity: ApiPagination?): Pagination {
        return Pagination(
            currentPage = apiEntity?.currentPage ?: 0,
            totalPages = apiEntity?.totalPages ?: 0
        )
    }
}