package com.marcskow.url.shortener.user.exception

data class UserNotFoundException(override val message: String) : Exception(message)