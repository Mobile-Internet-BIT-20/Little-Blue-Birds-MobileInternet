package com.lwy.bluebird.Fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.lwy.bluebird.Data.responseModel
import com.lwy.bluebird.Data.userLogin
import com.lwy.bluebird.R
import com.lwy.bluebird.Service.bbWebService
import com.lwy.bluebird.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.security.MessageDigest
import retrofit2.Callback
import java.io.IOException

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

            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val securePw = md5(password)
            var user = userLogin(email,securePw)

            val retrofit = bbWebService.service
            retrofit.login(user).enqueue(object : Callback<responseModel> {
                override fun onResponse(
                    call: Call<responseModel>,
                    response: Response<responseModel>
                ) {
                    Toast.makeText(requireContext(), "登入成功", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }

                override fun onFailure(call: Call<responseModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "登入失败，请重新尝试", Toast.LENGTH_SHORT).show()
                }
            })
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
