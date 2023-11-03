package cz.ackee.testtask.rm.app.common

typealias EmptyResponse = Response<Unit>

sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<T> (
        val data: T
    ): Response<T>()

    data class Error(
        val error: UiText
    ): Response<Nothing>()

    fun getSuccessData(): T? {
        return if (this is Success) {
            data
        } else {
            null
        }
    }

}
