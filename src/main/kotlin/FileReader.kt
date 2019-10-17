package main

import java.io.File
import java.io.InputStream
import java.nio.file.Paths

fun readFile(): MutableList<String>{
    val currentPath = Paths.get("").toAbsolutePath().toString()
    val inputStream: InputStream = File("$currentPath/src/main/resources/nodes-list").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
    return lineList
}