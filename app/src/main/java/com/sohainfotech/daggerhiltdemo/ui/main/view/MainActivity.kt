package com.sohainfotech.daggerhiltdemo.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohainfotech.daggerhiltdemo.R
import com.sohainfotech.daggerhiltdemo.data.model.User
import com.sohainfotech.daggerhiltdemo.databinding.ActivityMainBinding
import com.sohainfotech.daggerhiltdemo.ui.main.adapter.MainAdapter
import com.sohainfotech.daggerhiltdemo.ui.main.viewmodel.MainViewModel
import com.sohainfotech.daggerhiltdemo.utils.Status
import dagger.hilt.android.AndroidEntryPoint

//Step 10.

/**
 * Here, @AndroidEntryPoint means Dagger-Hilt can now inject dependencies in this class.
 */

/**
 * @AndroidEntryPoint annotation can be used in,

Activity
Fragment
View
Service
BroadcastReceiver

Hilt currently only supports activities that extend ComponentActivity and fragments that extend androidx library Fragment.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    //To use View Binding in Activity, create an instance of the binding class, get the root view,
    // and pass it to setContentView().
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //To use View Binding in Activity
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let {
                        users -> renderList(users)
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}