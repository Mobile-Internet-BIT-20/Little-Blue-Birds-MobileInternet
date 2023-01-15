package com.lwy.bluebird.Fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.lwy.bluebird.Data.responseModel
import com.lwy.bluebird.Data.userLogin
import com.lwy.bluebird.Data.userRegister
import com.lwy.bluebird.R
import com.lwy.bluebird.Service.bbWebService
import com.lwy.bluebird.databinding.FragmentProfileBinding
import com.lwy.bluebird.databinding.FragmentSignupBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.security.MessageDigest

class SignupFragment : Fragment() {

    private  var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.signupButton.setOnClickListener {

            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val securePw = md5(password)
            var user = userRegister(email,securePw)

            val retrofit = bbWebService.service
            retrofit.register(user).enqueue(object : Callback<responseModel> {
                override fun onResponse(
                    call: Call<responseModel>,
                    response: Response<responseModel>
                ) {
                    Toast.makeText(requireContext(), response.message().toString(), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                }

                override fun onFailure(call: Call<responseModel>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.signupLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        return binding.root
    }

    private fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length -2))
        }
        return hex.toString()
    }
}