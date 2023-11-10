package com.example.coffeeshop.untils

import java.io.File

actual fun getImageBytes(): ByteArray {
    return File("lolka").readBytes()
}