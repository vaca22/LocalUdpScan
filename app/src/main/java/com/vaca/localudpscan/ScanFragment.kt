package com.vaca.localudpscan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vaca.localudpscan.databinding.FragmentScanBinding
import com.vaca.localudpscan.net.NetSetting.targetIp
import com.vaca.localudpscan.net.NetUtils
import com.vaca.localudpscan.net.UdpServer
import kotlinx.coroutines.launch

class ScanFragment:Fragment() {
    lateinit var binding:FragmentScanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentScanBinding.inflate(inflater,container,false)




        binding.fuck.setOnClickListener {
            NetUtils.dataScope.launch {
                if(targetIp!=null){
                    UdpServer.sendToTarget(byteArrayOf(97))
                }else{
                    UdpServer.broadCastMe()
                }

            }

        }






        return binding.root
    }
}