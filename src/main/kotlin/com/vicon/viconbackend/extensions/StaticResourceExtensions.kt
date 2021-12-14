package com.vicon.viconbackend.extensions

import com.vicon.viconbackend.domain.staticres.StaticResource
import org.springframework.data.domain.Page
import java.net.URL

const val CDN_PATH = "https://static.clonet.co.kr"

fun String.toCDNPath(): String {
    return if (this.isEmpty()) "" else "$CDN_PATH${URL(this).path}"
}

fun <T : StaticResource> Page<T>.convert1(): Page<T> = this.map { it.apply { toStaticPath() } }

fun <T : StaticResource> Iterable<T>.convert2(): Iterable<T> = this.map { it.apply { toStaticPath() } }

fun <T : StaticResource> T.convert3(): T = this.apply { toStaticPath() }