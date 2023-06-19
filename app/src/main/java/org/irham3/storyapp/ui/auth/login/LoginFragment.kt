package org.irham3.storyapp.ui.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.databinding.FragmentLoginBinding
import org.irham3.storyapp.ui.main.MainActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            tvToRegister.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
            )

            btnLogin.setOnClickListener {
                login()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun login() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        viewModel.login(email, password).observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Login Berhasil",
                        Toast.LENGTH_SHORT).show()
                    result.data?.loginResult?.token?.let { token ->
                        viewModel.saveAuthToken(token)
                    }

                    startActivity(
                        Intent(requireContext(), MainActivity::class.java)
                    )
                    requireActivity().finish()
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), result.message.toString(),
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}