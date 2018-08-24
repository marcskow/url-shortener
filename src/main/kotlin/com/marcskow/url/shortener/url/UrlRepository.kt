package com.marcskow.url.shortener.url

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : CrudRepository<Url, Long> {
    fun findByUrl(url: String): Url?
}