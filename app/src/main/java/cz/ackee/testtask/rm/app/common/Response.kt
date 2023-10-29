package cz.ackee.testtask.rm.app.common

sealed class Response<out T> {
    object Loading: Response<Nothing>()

    data class Success<T> (
        val data: T
    ): Response<T>()

    data class Error(
        val error: UiText
    ): Response<Nothing>()
}
