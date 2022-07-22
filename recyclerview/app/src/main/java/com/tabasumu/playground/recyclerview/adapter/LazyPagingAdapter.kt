package com.tabasumu.playground.recyclerview.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Recyclerview
 * @author Mambo Bryan
 * @email mambobryan@gmail.com
 * Created 7/22/22 at 9:07 AM
 */
class LazyPagingAdapter<T : Any, R : ViewBinding>(
    val create: (parent: ViewGroup) -> R,
    val bind: (R.(item: T) -> Unit)? = null,
    val selected: ((item: T) -> Unit)? = null,
    val comparator: DiffUtil.ItemCallback<Any> = DEFAULT_COMPARATOR
) : PagingDataAdapter<Any, LazyPagingAdapter<T, R>.LazyViewHolder>(comparator) {

    inner class LazyViewHolder(val binding: R) : RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                selected?.invoke(getItem(absoluteAdapterPosition) as T)
            }

        }

        fun bindHolder(item: T) {
            bind?.let { block -> binding.block(item) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LazyViewHolder {
        val binding = create.invoke(parent)
        return LazyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LazyViewHolder, position: Int) {
        val item = getItem(position) as T
        holder.bindHolder(item)
    }

}