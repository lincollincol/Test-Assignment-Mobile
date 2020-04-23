package linc.com.jsonnavigator.utils

import java.lang.StringBuilder

class PathFormatter {

    // todo refactor
    private val items = mutableListOf<String>()

    fun addNew(name: String) {
        if(!items.contains(name)) {
            val newPath = when {
                name.isEmpty() -> ""
                name == "/" -> name
                else -> "$name/"
            }
            items.add(newPath)
        }
    }

    fun removaLast() {
        if(items.lastIndex > 0) {
            items.removeAt(items.lastIndex)
        }
    }

    fun getPath() = StringBuilder().apply {
        items.forEach {
            name -> append(name)
        }
    }.toString()

    companion object {
        fun getFileExt(name: String): String {
            val index = name.lastIndexOf(".")
            return name.substring(index)
        }
    }

}