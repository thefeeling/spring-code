package me.daniel.springjpapersistencecontext.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class NotExistsOrderException(val msg: String) : RuntimeException(msg)