package com.marcskow.url.shortener.general

data class PageableList<T>(val result: List<T>, val total: Long)