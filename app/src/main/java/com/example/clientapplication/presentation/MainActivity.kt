package com.example.clientapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.clientapplication.R
import com.example.clientapplication.databinding.ActivityMainBinding
import com.example.clientapplication.databinding.CardViewBinding
import com.example.entity.Repository

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MyAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.list.observe(this) {
            Log.d("test", it.toString())
            adapter.submitList(it)
        }
    }

    class MyAdapter: androidx.recyclerview.widget.ListAdapter<Repository, MyAdapter.SampleViewHolder>(
        object: DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean = oldItem == newItem
        }
    ) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder =
            SampleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_view, null, false))
        override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
            val repository = getItem(position)
            holder.binding?.let {
                it.imageView.load(repository.url)
                it.repository = repository
                it.executePendingBindings()
            }
        }

        class SampleViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val binding: CardViewBinding? = DataBindingUtil.bind(view)
        }
    }

}
