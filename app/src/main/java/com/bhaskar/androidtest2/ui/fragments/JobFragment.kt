package com.bhaskar.androidtest2.ui.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bhaskar.androidtest2.R
import com.bhaskar.androidtest2.databinding.CustomAlertLayoutBinding
import com.bhaskar.androidtest2.databinding.FragmentJobBinding
import com.bhaskar.androidtest2.objects.Constant
import com.bhaskar.androidtest2.objects.FragmentPosition
import com.bhaskar.androidtest2.ui.MainActivity

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
        with(binding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.emailPhoneRequestDemoButton -> {
                        if (sharedCredentials.getString("company", null)?.trim()?.isEmpty() == true) {
                            companyLayout.error = "Company name required"
                            return@OnClickListener
                        }
                        if (sharedCredentials.getString("job", null)?.trim()?.isEmpty() == true) {
                            jobLayout.error = "Title required"
                            return@OnClickListener
                        }
                        showDialog()
                    }
                }
            }
            jobEditCompanyName.addTextChangedListener {
                sharedEdit.putString("company", it.toString().trim()).apply()
                companyLayout.error = null
            }
            jobEditJobTitle.addTextChangedListener {
                jobLayout.error = null
                sharedEdit.putString("job", it.toString().trim()).apply()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        val dialogBinding: CustomAlertLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireActivity()),
            R.layout.custom_alert_layout,
            null,
            false
        )
        val customDialog = Dialog(requireActivity())
        val intent = Intent(requireActivity(), MainActivity::class.java)
        customDialog.apply {
            setContentView(dialogBinding.root)
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }.show()
        with (dialogBinding) {
            onItemClick = View.OnClickListener { view ->
                when (view.id) {
                    R.id.dismissButton -> {
                        customDialog.dismiss()
                        sharedEdit.clear().apply()
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }
            }
            when (sharedCredentials.getInt("welcome", 0)) {
                1 -> welcomeAnswer.text = getString(R.string.lead_generation_bots)
                2 -> welcomeAnswer.text = getString(R.string.multi_step_forms)
                3 -> welcomeAnswer.text = getString(R.string.integrations)
            }
            when (sharedCredentials.getInt("department", 0)) {
                1 -> departmentAnswer.text = getString(R.string.marketing)
                2 -> departmentAnswer.text = getString(R.string.sales)
                3 -> departmentAnswer.text = getString(R.string.customer_service)
            }
            emailPhoneAnswerEmail.text = sharedCredentials.getString("email", null)
            emailPhoneAnswerPhone.text = sharedCredentials.getString("phone", null)
            nameAnswer.text = sharedCredentials.getString("first-name", null) + getString(R.string.space) + sharedCredentials.getString("last-name", null)
            jobAnswerCompany.text = sharedCredentials.getString("company", null)
            jobAnswerTitle.text = sharedCredentials.getString("job", null)
        }
    }

    override fun onResume() {
        super.onResume()
        FragmentPosition.position.value = 5
        with(binding) {
            jobEditCompanyName.setText(
                sharedCredentials.getString("company", null),
                TextView.BufferType.EDITABLE
            )
            jobEditJobTitle.setText(
                sharedCredentials.getString("job", null),
                TextView.BufferType.EDITABLE
            )
        }
    }
}