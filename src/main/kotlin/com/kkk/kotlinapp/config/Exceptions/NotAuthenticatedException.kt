package com.kkk.kotlinapp.config.Exceptions

class NotAuthenticatedException: RuntimeException{
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)

}