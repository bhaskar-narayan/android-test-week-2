package com.bhaskar.androidtest2.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.FragmentJobBinding
import com.bhaskar.androidtest2.objects.Constant
import com.bhaskar.androidtest2.objects.FragmentPosition

class JobFragment : Fragment() {
    private lateinit var binding: FragmentJobBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            jobEditCompanyName.addTextChangedListener {
                sharedEdit.putString("company", it.toString()).apply()
            }
            jobEditJobTitle.addTextChangedListener {
                sharedEdit.putString("job", it.toString()).apply()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FragmentPosition.position.value = 5
        with (binding) {
            jobEditCompanyName.setText(sharedCredentials.getString("company", null), TextView.BufferType.EDITABLE);
            jobEditJobTitle.setText(sharedCredentials.getString("job", null), TextView.BufferType.EDITABLE);
        }
    }
}