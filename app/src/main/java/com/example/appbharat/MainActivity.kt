package com.example.appbharat

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
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

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(itemClickListener = {
            Toast.makeText(
                this, "this${it}", Toast.LENGTH_SHORT
            ).show()

        }, this)
    }



    override fun readArguments(extras: Intent) {

    }

    override fun setupUi() {
        mainViewModel.getMemes()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.memeRV.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.memeRV.adapter = mainAdapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.memeRV)

    }

    override fun observeData() {
        collectEvent(mainViewModel._memeData) {
            Log.d("SHAW_TAG", "observeData: " + it)
            it?.data?.memes.let {
                mainAdapter.submitList(it)
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


