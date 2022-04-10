package com.bhaskar.androidtest2.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentNameBinding
import com.bhaskar.androidtest2.objects.Constant
import com.bhaskar.androidtest2.objects.Constant.NAME
import com.bhaskar.androidtest2.objects.FragmentPosition
import java.util.regex.Pattern

class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    private lateinit var sharedCredentials: SharedPreferences
    private lateinit var sharedEdit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedCredentials = requireActivity().getSharedPreferences(Constant.SHARED_CREDENTIAL, 0)
        sharedEdit = sharedCredentials.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.nameNextButton -> {
                        val pattern = Pattern.compile(NAME)
                        if (sharedCredentials.getString("first-name", null)
                                ?.let { pattern.matcher(it).matches() } == false
                        ) {
                            firstNameLayout.error = "Invalid name"
                            return@OnClickListener
                        }
                        if (sharedCredentials.getString("last-name", null)
                                ?.let { pattern.matcher(it).matches() } == false
                        ) {
                            lastNameLayout.error = "Invalid name"
                            return@OnClickListener
                        }
                        Navigation.findNavController(view)
                            .navigate(R.id.action_nameFragment_to_jobFragment)
                    }
                }
            }
            nameEditFirstName.addTextChangedListener {
                firstNameLayout.error = null
                sharedEdit.putString("first-name", it.toString().trim()).apply()
            }
            nameEditSecondName.addTextChangedListener {
                lastNameLayout.error = null
                sharedEdit.putString("last-name", it.toString().trim()).apply()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FragmentPosition.position.value = 4
        with(binding) {
            nameEditFirstName.setText(
                sharedCredentials.getString("first-name", null),
                TextView.BufferType.EDITABLE
            )
            nameEditSecondName.setText(
                sharedCredentials.getString("last-name", null),
                TextView.BufferType.EDITABLE
            )
        }
    }
}