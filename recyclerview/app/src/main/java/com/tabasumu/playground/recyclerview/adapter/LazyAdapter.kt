package com.tabasumu.playground.recyclerview.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Recyclerview
 * @author Mambo Bryan
 * @email mambobryan@gmail.com
 * Created 7/22/22 at 9:02 AM
 */
val DEFAULT_COMPARATOR = object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean =
        oldItem == newItem
}

class LazyAdapter<T : Any, R : ViewBinding>(
    val create: (parent: ViewGroup) -> R,
    val bind: (R.(item: T) -> Unit)? = null,
    val selected: ((item: T) -> Unit)? = null,
    val comparator: DiffUtil.ItemCallback<Any> = DEFAULT_COMPARATOR
) : ListAdapter<Any, LazyAdapter<T, R>.LazyViewHolder>(comparator) {

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