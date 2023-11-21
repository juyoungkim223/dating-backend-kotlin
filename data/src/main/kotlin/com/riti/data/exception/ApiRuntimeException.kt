package com.riti.data.exception

import com.riti.data.enums.ResultCode

class ApiRuntimeException: RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(resultCode: ResultCode) : super(resultCode.resultCode)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}