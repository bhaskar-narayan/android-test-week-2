package com.bhaskar.androidtest2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentDepartmentBinding
import com.bhaskar.androidtest2.objects.FragmentPosition
import com.bhaskar.androidtest2.objects.FragmentPosition.position

class DepartmentFragment : Fragment() {
    private lateinit var binding: FragmentDepartmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    R.id.departmentFirstButton -> Navigation.findNavController(view).navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                    R.id.departmentSecondButton -> Navigation.findNavController(view).navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                    R.id.departmentThirdButton -> Navigation.findNavController(view).navigate(R.id.action_departmentFragment_to_emailPhoneFragment)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        position.value = 2
    }
}