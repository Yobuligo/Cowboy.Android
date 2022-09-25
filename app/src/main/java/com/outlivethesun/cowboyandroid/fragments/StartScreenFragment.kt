package com.outlivethesun.cowboyandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.outlivethesun.cowboyandroid.R
import com.outlivethesun.cowboyandroid.databinding.FragmentStartScreenBinding
import com.outlivethesun.cowboyandroid.stockMarket.ProfileViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartScreenFragment : Fragment() {
    private var _binding: FragmentStartScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startScreenEdittextName.addTextChangedListener { editText ->
            viewModel.username = editText.toString()
        }
        binding.startScreenButtonStartGame.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}