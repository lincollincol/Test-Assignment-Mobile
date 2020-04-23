package linc.com.jsonnavigator.utils


fun <E> MutableList<E>.updateAll(newList: List<E>) {
    this.clear()
    this.addAll(newList)
}

fun <T> T.toList() = mutableListOf<T>().apply {
    add(this@toList)
}
