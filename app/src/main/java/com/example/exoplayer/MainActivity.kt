package com.example.exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exoplayer.adapter.VideoAdapter
import com.example.exoplayer.databinding.ActivityMainBinding
import com.example.exoplayer.service.Repository
import com.example.exoplayer.service.VideoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var videoAdapter: VideoAdapter

    private val scope = CoroutineScope(Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, PlayerFragment())
            .commit()

        videoAdapter = VideoAdapter(callback = { url, title ->

            supportFragmentManager.fragments.find { it is PlayerFragment }?.let {
                (it as PlayerFragment).play(url, title)
            }

        })

        initRecyclerView()

        getVideoList()


    }

    private fun initRecyclerView() = with(binding) {
        mainRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getVideoList() {

        val retrofit = Repository.retrofit

        scope.launch {
            retrofit.create(VideoService::class.java).also {
                it.listVideos().body()?.let { videoDto ->
                    videoAdapter.setList(videoDto.videos)

                }
            }
        }

    }
}
