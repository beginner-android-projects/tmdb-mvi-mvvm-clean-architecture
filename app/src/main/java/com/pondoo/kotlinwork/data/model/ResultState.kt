package com.pondoo.kotlinwork.data.model

data class ResultState<out T>(val status: Status, val data: T?, val error: Error?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        inline fun <T, R> ResultState<T>.map(transform: (T) -> R): ResultState<R> {
            when(status){
                Status.SUCCESS ->return ResultState(Status.SUCCESS, transform(data!!), null)
                Status.LOADING ->return ResultState(Status.LOADING, null, null)
                Status.ERROR -> return ResultState(Status.ERROR, null, error)
            }
        }
        fun <T> success(data: T?): ResultState<T> {
            return ResultState(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Error?): ResultState<T> {
            return ResultState(Status.ERROR, null, error)
        }

        fun <T> loading(data: T? = null): ResultState<T> {
            return ResultState(Status.LOADING, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error)"
    }
}

