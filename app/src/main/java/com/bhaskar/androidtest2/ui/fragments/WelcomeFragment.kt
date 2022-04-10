package com.bhaskar.androidtest2.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentWelcomeBinding
import com.bhaskar.androidtest2.objects.Constant.SHARED_CREDENTIAL
import com.bhaskar.androidtest2.objects.FragmentPosition.position

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var sharedCredentials: SharedPreferences
    private lateinit var sharedEdit: SharedPreferences.Editor
    private var selectedOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedCredentials = requireActivity().getSharedPreferences(SHARED_CREDENTIAL, 0)
        sharedEdit = sharedCredentials.edit()
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
                    R.id.welcomeFirstButton -> {
                        sharedEdit.putInt("welcome", 1).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_welcomeFragment_to_departmentFragment)
                    }
                    R.id.welcomeSecondButton -> {
                        sharedEdit.putInt("welcome", 2).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_welcomeFragment_to_departmentFragment)
                    }
                    R.id.welcomeThirdButton -> {
                        sharedEdit.putInt("welcome", 3).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_welcomeFragment_to_departmentFragment)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        position.value = 1
        selectedOption = sharedCredentials.getInt("welcome", 0)
        with (binding) {
            when (selectedOption) {
                1 -> welcomeFirstButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
                2 -> welcomeSecondButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
                3 -> welcomeThirdButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
            }
        }
    }
}