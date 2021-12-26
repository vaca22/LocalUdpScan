package com.vaca.localudpscan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vaca.localudpscan.databinding.FragmentScanBinding

class ScanFragment:Fragment() {
    lateinit var binding:FragmentScanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentScanBinding.inflate(inflater,container,false)
        return binding.root
    }
}