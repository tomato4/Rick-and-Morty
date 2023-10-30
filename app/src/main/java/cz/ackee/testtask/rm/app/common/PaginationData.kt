package cz.ackee.testtask.rm.app.common


data class PaginationData<T>(
    val data: List<T> = emptyList(),
    val lastData: Response<List<T>> = Response.Success(emptyList()),
    val page: Int = 0,
    val endReached: Boolean = false,
    val loadMore: () -> Unit = {}
) {
    val canPaginate = !endReached && lastData !is Response.Loading

    fun getNextPaginationData(): PaginationData<T> {
        return if (endReached) {
            this
        } else if (lastData is Response.Error) {
            this.copy(lastData = Response.Loading)
        } else {
            this.copy(lastData = Response.Loading, page = page + 1)
        }
    }

    fun isLoadingPage() = lastData is Response.Loading
}