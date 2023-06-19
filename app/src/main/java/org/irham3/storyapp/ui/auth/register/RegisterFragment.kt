package org.irham3.storyapp.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.irham3.storyapp.R
import org.irham3.storyapp.data.Result
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

            tvToLogin.setOnClickListener {
                navigateToLogin()
            }

            btnRegister.setOnClickListener {
                register()
            }
        }
    }

    private fun register() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        viewModel.register(name, email, password).observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.message.let {
                        Toast.makeText(requireContext(), it,
                            Toast.LENGTH_SHORT).show()
                    }
                    navigateToLogin()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), result.data.toString(),
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToLogin() {
        view!!.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}