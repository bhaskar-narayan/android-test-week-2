package com.bhaskar.androidtest2.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentDepartmentBinding
import com.bhaskar.androidtest2.objects.Constant
import com.bhaskar.androidtest2.objects.FragmentPosition
import com.bhaskar.androidtest2.objects.FragmentPosition.position

class DepartmentFragment : Fragment() {
    private lateinit var binding: FragmentDepartmentBinding
    private lateinit var sharedCredentials: SharedPreferences
    private lateinit var sharedEdit: SharedPreferences.Editor
    private var selectedOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedCredentials = requireActivity().getSharedPreferences(Constant.SHARED_CREDENTIAL, 0)
        sharedEdit = sharedCredentials.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_department, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.departmentFirstButton -> {
                        sharedEdit.putInt("department", 1).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                    }
                    R.id.departmentSecondButton -> {
                        sharedEdit.putInt("department", 2).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                    }
                    R.id.departmentThirdButton -> {
                        sharedEdit.putInt("department", 3).apply()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        position.value = 2
        selectedOption = sharedCredentials.getInt("department", 0)
        with (binding) {
            when (selectedOption) {
                1 -> departmentFirstButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
                2 -> departmentSecondButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
                3 -> departmentThirdButton.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button_tint))
            }
        }
    }
}