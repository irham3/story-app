package org.irham3.storyapp.ui.auth.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.databinding.FragmentLoginBinding
import org.irham3.storyapp.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            tvToLogin.setOnClickListener {
                navigateToLogin()
            }

            btnRegister.setOnClickListener {
                register(name, email, password)
            }
        }
    }

    private fun register(name: String, email: String, password: String) {
        viewModel.register(name, email, password).observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    result.data?.message.let {
                        Toast.makeText(requireContext(), it,
                            Toast.LENGTH_SHORT).show()
                    }

                    navigateToLogin()
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), result.data.toString(),
                        Toast.LENGTH_SHORT).show()
                    Log.e("error", result.message.toString())
                }
            }
        }
    }

    private fun navigateToLogin() {
        Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
    }
}