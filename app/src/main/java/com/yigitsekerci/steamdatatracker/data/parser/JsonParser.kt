package com.yigitsekerci.steamdatatracker.data.parser

interface JsonParser<T> {
    suspend fun parse(jsonString: String): T?
}