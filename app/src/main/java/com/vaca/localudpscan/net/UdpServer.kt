package com.vaca.localudpscan.net

import android.util.Log
import com.vaca.localudpscan.MainActivity
import com.vaca.localudpscan.net.NetSetting.gate
import com.vaca.localudpscan.net.NetSetting.myIp
import com.vaca.localudpscan.net.NetUtils.bytebuffer2ByteArray
import com.vaca.localudpscan.net.NetUtils.fillString
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

object UdpServer {
    private val bufReceive: ByteBuffer = ByteBuffer.allocate(1200)

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






    private fun startListen() {
        while (true) {
            try {
                bufReceive.clear()
                val sourceAddress=channel.receive(bufReceive) as InetSocketAddress
                val bytes=bytebuffer2ByteArray(bufReceive)
                Log.e("fuck","size  "+bytes.size)
                //send2Destination(bytes, "192.168.6.102", 8888)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    private fun send2Destination(message: String, ip: String, port: Int) {
        try {
            val buf: ByteBuffer = ByteBuffer.allocate(600)
            val configInfo = message.toByteArray()
            buf.clear()
            buf.put(configInfo)
            buf.flip()
          //  Log.e("fuck",ip)
            channel.send(buf, InetSocketAddress(ip, port))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun send2Destination(msgBytes: ByteArray, ip: String, port: Int) {
        try {
            val buf: ByteBuffer = ByteBuffer.allocate(2000)
            buf.clear()
            buf.put(msgBytes)
            buf.flip()
            channel.send(buf, InetSocketAddress(ip, port))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun broadCastMe(){
        for(k in 1..30){
            val fuck=NetSetting.gateX+ fillString(k,2)
            if(fuck!=myIp){
                send2Destination("fuck me",fuck, localPort)
            }
        }
    }



    fun udpServerStart() {
        System.out.println("fuck")
        initUdp()
        Thread{
            Thread.sleep(100)
            startListen()
        }.start()
        NetUtils.dataScope.launch {
            broadCastMe()
        }
    }
}