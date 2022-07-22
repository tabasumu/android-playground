package com.tabasumu.playground.recyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.tabasumu.playground.recyclerview.adapter.LazyAdapter
import com.tabasumu.playground.recyclerview.databinding.ActivityMainBinding
import com.tabasumu.playground.recyclerview.databinding.LayoutItemBinding
import com.tabasumu.playground.recyclerview.utils.getInflater

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val data = MutableLiveData<List<Any?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDummyData()

        initRecyclerview()

    }

    private fun loadDummyData() {
        val list = listOf<String>(
            "one",
            "two",
            "three",
            "four",
            "five"
        )

        data.value = list
    }

    private fun initRecyclerview() {

        val adapter = LazyAdapter<String, LayoutItemBinding>(
            create = { LayoutItemBinding.inflate(it.getInflater(), it, false) },
            bind = { item: String ->
                this.tvString.text = item
                this.tvString.setOnClickListener {
                    Snackbar.make(binding.recyclerview, item, Snackbar.LENGTH_SHORT).show()
                }
            }
        )

        binding.apply {
            recyclerview.adapter = adapter
        }

        data.observe(this) { list ->
            adapter.submitList(list)
        }

    }

}
