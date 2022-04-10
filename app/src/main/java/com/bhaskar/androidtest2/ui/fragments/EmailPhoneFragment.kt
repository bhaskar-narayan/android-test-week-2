package com.bhaskar.androidtest2.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentEmailPhoneBinding
import com.bhaskar.androidtest2.objects.Constant
import com.bhaskar.androidtest2.objects.FragmentPosition

class EmailPhoneFragment : Fragment() {
    private lateinit var binding: FragmentEmailPhoneBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email_phone, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.emailPhoneNextButton -> Navigation.findNavController(view).navigate(R.id.action_emailPhoneFragment_to_nameFragment)
                }
            }
            emailPhoneEditEmail.addTextChangedListener {
                sharedEdit.putString("email", it.toString()).apply()
            }
            emailPhoneEditPhone.addTextChangedListener {
                sharedEdit.putString("phone", it.toString()).apply()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FragmentPosition.position.value = 3
        with (binding) {
            emailPhoneEditEmail.setText(sharedCredentials.getString("email", null), TextView.BufferType.EDITABLE);
            emailPhoneEditPhone.setText(sharedCredentials.getString("phone", null), TextView.BufferType.EDITABLE);
        }
    }
}