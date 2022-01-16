package com.cplaygr.sample.sharedviewmodelonactivitysample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cplaygr.sample.sharedviewmodelonactivitysample.R
import com.cplaygr.sample.sharedviewmodelonactivitysample.databinding.ActivityMainBinding
import com.cplaygr.sample.sharedviewmodelonactivitysample.viewmodel.PlayerDataViewModel
import com.cplaygr.sample.sharedviewmodelonactivitysample.model.PlayerData

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "_MainActivity"
    }

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var playerDataViewModel: PlayerDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
    }

    override fun onStart() {
        super.onStart()
        setViewModel()
        setViewModelData()
        commitFragment()
    }

    private fun setViewModel() {
        playerDataViewModel = ViewModelProvider(this).get(PlayerDataViewModel::class.java)
        val playerDataObserver: Observer<PlayerData> = Observer {
            Log.d(TAG, "setViewModel() called name: ${it.name}, age: ${it.age}")
        }
        playerDataViewModel.playerDataLiveData.observe(this, playerDataObserver)
    }

    private fun setViewModelData() {
        playerDataViewModel.playerDataLiveData.postValue(PlayerData("Ji Sung Park", "38"))
    }

    private fun commitFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(mainActivityBinding.mainFragment.id, SharedMainFragment())
            .replace(mainActivityBinding.subFragment.id, SharedSubFragment())
            .commit()
    }
}