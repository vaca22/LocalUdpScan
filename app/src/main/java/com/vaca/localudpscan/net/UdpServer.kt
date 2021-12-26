package com.vaca.localudpscan.net

import android.util.Log
import com.vaca.localudpscan.MainActivity
import com.vaca.localudpscan.net.NetSetting.gate
import com.vaca.localudpscan.net.NetSetting.myIp
import com.vaca.localudpscan.net.NetUtils.bytebuffer2ByteArray
import com.vaca.localudpscan.net.NetUtils.fillString
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

object UdpServer {
    private const val localPort = 13207
    private lateinit var channel: DatagramChannel
    private fun initUdp() {
        try {
            channel = DatagramChannel.open();
            channel.socket().bind(InetSocketAddress(localPort));
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }






    private suspend fun startListen() {
        while (true) {
                try {
                    bufR.clear()
                    val sourceAddress=channel.receive(bufR) as InetSocketAddress
                    val bytes=bytebuffer2ByteArray(bufR)
                    Log.e("fuck","size  "+bytes.size)
                    //send2Destination(bytes, "192.168.6.102", 8888)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
        }
    }

    val buf: ByteBuffer = ByteBuffer.allocate(500)
    val bufR: ByteBuffer = ByteBuffer.allocate(500)
    val mLock= Mutex()

    private suspend fun send2Destination(message: String, ip: String, port: Int) {
        mLock.withLock {
            try {
                val configInfo = message.toByteArray()
                buf.clear()
                buf.put(configInfo)
                buf.flip()
                channel.send(buf, InetSocketAddress(ip, port))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private suspend fun send2Destination(msgBytes: ByteArray, ip: String, port: Int) {
        mLock.withLock {
            try {
                buf.clear()
                buf.put(msgBytes)
                buf.flip()
                channel.send(buf, InetSocketAddress(ip, port))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    suspend fun broadCastMe(){
        for(k in 0..255){
            val fuck=NetSetting.gateX+ fillString(k,3)
            if(fuck!=myIp){
                send2Destination("fuck me",fuck, localPort)
            }
        }
    }



    fun udpServerStart() {
        System.out.println("fuck")
        initUdp()
        NetUtils.dataScope.launch {
           // broadCastMe()
            send2Destination("fuck",gate, localPort)

        }
        NetUtils.dataScope.launch {
            delay(100)
            startListen()
        }
    }
}