package linc.com.jsonnavigator.utils

import linc.com.jsonnavigator.utils.Constants.Companion.DEFAULT
import linc.com.jsonnavigator.utils.Constants.Companion.EXT_DIVIDER
import linc.com.jsonnavigator.utils.Constants.Companion.NOTHING
import linc.com.jsonnavigator.utils.Constants.Companion.SEPARATOR
import java.lang.StringBuilder

class PathFormatter {

    private val items = mutableListOf<String>(DEFAULT)

    fun addNew(name: String) {
        if(!items.contains(name)) {
            val newPath = when {
                name.isEmpty() -> NOTHING
                name == SEPARATOR -> name
                else -> "$name$SEPARATOR"
            }
            items.add(newPath)
        }
        if(items.lastIndex > 1 && items.contains(DEFAULT)) {
            items.remove(DEFAULT)
        }
    }

    fun removeLast() {
        if(items.lastIndex > 0) {
            items.removeAt(items.lastIndex)
        }
        if(items.lastIndex < 1) {
            items.add(DEFAULT)
        }
    }

    fun getPath() = StringBuilder().apply {
        items.forEach {
            name -> append(name)
        }
    }.toString()

    companion object {
        fun getFileExt(name: String): String {
            val index = name.lastIndexOf(EXT_DIVIDER)
            return name.substring(index)
        }
    }

}