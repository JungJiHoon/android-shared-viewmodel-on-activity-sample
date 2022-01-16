package com.cplaygr.sample.sharedviewmodelonactivitysample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.cplaygr.sample.sharedviewmodelonactivitysample.databinding.FragmentSharedMainBinding
import com.cplaygr.sample.sharedviewmodelonactivitysample.viewmodel.PlayerDataViewModel
import com.cplaygr.sample.sharedviewmodelonactivitysample.model.PlayerData

class SharedMainFragment : Fragment() {

    companion object {
        private const val TAG = "SharedMainFragment"
    }

    private val playerDataViewModel: PlayerDataViewModel by activityViewModels()
    private lateinit var sharedMainFragmentBinding: FragmentSharedMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedMainFragmentBinding =
            FragmentSharedMainBinding.inflate(layoutInflater, container, false)
        return sharedMainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setViewModel()
    }

    private fun setViewModel() {
        val playerDataObserver: Observer<PlayerData> = Observer {
            Log.d(TAG, "setViewModel() called name: ${it.name}, age: ${it.age}")
        }
        playerDataViewModel.playerDataLiveData.observe(viewLifecycleOwner, playerDataObserver)
    }

    private fun initListeners() {
        sharedMainFragmentBinding.addPlayer.setOnClickListener {
            playerDataViewModel.playerDataLiveData.postValue(
                PlayerData("Messi", "34")
            )
        }
    }
}