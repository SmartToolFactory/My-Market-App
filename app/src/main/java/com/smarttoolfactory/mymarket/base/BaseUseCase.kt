package com.smarttoolfactory.mymarket.base


abstract class BaseUseCase {

    /**
     * This method is required to dispose RxJava Observables
     */
    abstract fun dispose()
}