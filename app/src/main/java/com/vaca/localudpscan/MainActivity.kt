package com.vaca.localudpscan

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vaca.localudpscan.databinding.ActivityMainBinding
import com.vaca.localudpscan.net.NetUtils.intToIp

class MainActivity : AppCompatActivity() {
    lateinit var wifiManager: WifiManager
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wifiManager = MainApplication.application.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val gate=intToIp(wifiManager.dhcpInfo.gateway)
        if (gate != null) {
            Log.e("gate",gate)
        }
    }
}