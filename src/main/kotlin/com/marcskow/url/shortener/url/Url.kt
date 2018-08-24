package com.marcskow.url.shortener.url

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "urls")
class Url(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
          @NotNull val url: String)