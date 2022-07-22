package com.tabasumu.playground.recyclerview.utils

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Recyclerview
 * @author Mambo Bryan
 * @email mambobryan@gmail.com
 * Created 7/22/22 at 9:05 AM
 */

fun ViewGroup.getInflater(): LayoutInflater {
    return LayoutInflater.from(this.context)
}