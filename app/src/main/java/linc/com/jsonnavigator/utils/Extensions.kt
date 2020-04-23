package linc.com.jsonnavigator.utils


inline fun <T> T.isNull(func: ()-> T): T {
    return this ?: func()
}

fun <E> MutableList<E>.updateAll(newList: List<E>) {
    this.clear()
    this.addAll(newList)
}

fun <T> T.toList() = mutableListOf<T>().apply {
    add(this@toList)
}
