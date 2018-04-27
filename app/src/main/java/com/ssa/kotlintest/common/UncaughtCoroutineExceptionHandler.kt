package com.ssa.kotlintest.common

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

class UncaughtCoroutineExceptionHandler(val errorHandler: CoroutineErrorListener) :
        CoroutineExceptionHandler, AbstractCoroutineContextElement(CoroutineExceptionHandler.Key) {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        errorHandler.onError(exception)
    }
}