package com.bhaskar.androidtest2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentWelcomeBinding
import com.bhaskar.androidtest2.objects.FragmentPosition.position

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.welcomeFirstButton -> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_departmentFragment)
                    R.id.welcomeSecondButton -> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_departmentFragment)
                    R.id.welcomeThirdButton -> Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_departmentFragment)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        position.value = 1
    }
}