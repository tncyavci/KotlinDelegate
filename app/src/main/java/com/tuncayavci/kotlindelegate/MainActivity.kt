package com.tuncayavci.kotlindelegate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifeCycleLogger by LifeCycleLoggerImplementation() {

// by delegation you don't need to implement interface's functions

    //property delegation
    private val myVariables by lazy {
        // with by lazy,, until variables initialize, it doesn't use any location on memory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerLifeCycleOwner(this)
    }


}

interface LifeCycleLogger {

    fun registerLifeCycleOwner(owner: LifecycleOwner)
}

class LifeCycleLoggerImplementation : LifeCycleLogger, LifecycleEventObserver {
    override fun registerLifeCycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }
    }
}