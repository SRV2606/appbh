package com.example.appbharat

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.appbharat.databinding.ActivityMainBinding
import com.example.practice.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    private val mainAdapter by lazy {
        MainAdapter(itemClickListener = {

        }, context = this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun readArguments(extras: Intent) {

    }

    override fun setupUi() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.memeRV.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.memeRV.adapter = mainAdapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.memeRV)
    }

    override fun observeData() {
        collectEvent(mainViewModel._memeData) {
            it?.data?.let { memesList ->
                mainAdapter.submitList(memesList.memes)
            }
        }
    }

    override fun setListener() {

    }

}


fun <T> ComponentActivity.collectEvent(flow: Flow<T>, collect: suspend (T) -> Unit): Job {
    return lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.onEach(collect).collect()
        }
    }
}


