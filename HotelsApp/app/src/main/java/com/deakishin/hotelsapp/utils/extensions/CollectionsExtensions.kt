package com.deakishin.hotelsapp.utils.extensions

/** Removes all occurrences of the element from the list.
 * @param element Element to remove. */
fun <T> MutableList<T>.removeElementAll(element:T){
    removeAll(listOf(element))
}

/** Adds the element to the list if this element is not already in the list. */
fun <T> MutableList<T>.addIfNotContained(element:T){
    if (!contains(element)) add(element)
}