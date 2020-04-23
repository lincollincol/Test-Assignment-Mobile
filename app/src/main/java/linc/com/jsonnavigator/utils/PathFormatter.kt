package linc.com.jsonnavigator.utils

import java.lang.StringBuilder

class PathFormatter {

    private val items = mutableListOf<String>()

    fun addNew(name: String) {
        if(!items.contains(name)) {
            val newPath = if(name == "/") name else "$name/"
            items.add(newPath)
        }
    }

    fun removaLast() {
        items.removeAt(items.lastIndex)
    }

    fun getPath() = StringBuilder().apply {
        items.forEach {
            name -> append(name)
        }
    }.toString()

}