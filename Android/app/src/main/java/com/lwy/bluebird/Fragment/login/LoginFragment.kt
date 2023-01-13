package com.lwy.bluebird.Fragment.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lwy.bluebird.R
import com.lwy.bluebird.databinding.FragmentLoginBinding
import com.lwy.bluebird.databinding.FragmentProfileBinding

class LoginFragment : Fragment() {

    private  var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginSignupButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text
            val password = binding.loginPassword.text

            findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
        }

        return binding.root
    }
}