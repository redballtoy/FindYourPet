package com.example.redballtoy.findyourpet.common.domain.model.pagination

/*
Для случаев, когда мы сохраняем текущую страницу локально, но еще не запросили новую страницу
из удаленного источника. Общее количество страниц должно изменяться со временем, поэтому мы будем
обрабатывать значение как неизвестное перед обновлением.
*/

data class Pagination(
    val currentPage: Int,
    val totalPages: Int
) {
    companion object {
        const val UNKNOWN_TOTAL = -1
        const val DEFAULT_PAGE_SIZE = 20
    }

    val canLoadMode: Boolean
        get() = totalPages == UNKNOWN_TOTAL || currentPage < totalPages
}
