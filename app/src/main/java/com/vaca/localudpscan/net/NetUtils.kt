package com.vaca.localudpscan.net

object NetUtils {
    fun intToIp(paramInt: Int): String {
        return ((paramInt.and(255)).toString() + "." + (paramInt.shr(8).and(255)) + "." + (paramInt.shr(16).and(255)) + "."
                + (paramInt.shr(24).and(255)))
    }
    fun intToIp2(paramInt: Int): String {
        return ((paramInt.and(255)).toString() + "." + (paramInt.shr(8).and(255)) + "." + (paramInt.shr(16).and(255)) + ".")
    }
    fun intToIp3(paramInt: Int): Int {
        return (paramInt.shr(24).and(255))
    }
}