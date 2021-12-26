package com.vaca.localudpscan

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vaca.localudpscan.databinding.ActivityMainBinding
import com.vaca.localudpscan.net.NetSetting.gate
import com.vaca.localudpscan.net.NetSetting.gateX
import com.vaca.localudpscan.net.NetSetting.gatep
import com.vaca.localudpscan.net.NetUtils.intToIp
import com.vaca.localudpscan.net.NetUtils.intToIp2
import com.vaca.localudpscan.net.NetUtils.intToIp3
import com.vaca.localudpscan.net.UdpServer

class MainActivity : AppCompatActivity() {
    companion object{

    }
    lateinit var wifiManager: WifiManager
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wifiManager = MainApplication.application.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val gatew=wifiManager.dhcpInfo.gateway
        gate=intToIp(gatew)
        gatep= intToIp3(gatew)
        gateX= intToIp2(gatew)
        Log.e("gate",gate+"         "+ intToIp3(wifiManager.dhcpInfo.gateway)+"         "+ intToIp2(wifiManager.dhcpInfo.gateway))

        UdpServer.udpServerStart()




    }
}