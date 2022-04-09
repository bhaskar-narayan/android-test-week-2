package com.bhaskar.androidtest2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentJobBinding
import com.bhaskar.androidtest2.objects.FragmentPosition

class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        FragmentPosition.position.value = 5
    }
}