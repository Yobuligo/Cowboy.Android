package com.outlivethesun.cowboyandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.outlivethesun.cowboyandroid.assets.AssetsFactory
import com.outlivethesun.cowboyandroid.databinding.FragmentSecondBinding
import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.round.Move
import com.outlivethesun.cowboyandroid.round.Round
import com.outlivethesun.cowboyandroid.stockMarket.ProfileViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val assets = AssetsFactory().create()
    //    private val assets = AssetsFactoryDebug().create()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var round: IRound

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.recyclerViewResources.layoutManager = LinearLayoutManager(requireContext())

        round = Round(viewModel.name, assets)
        binding.recyclerViewResources.adapter =
            RecyclerViewAdapter(requireContext(), round, parentFragmentManager)
        binding.recyclerViewResources.addItemDecoration(
            DividerItemDecoration(
                activity, DividerItemDecoration.VERTICAL
            )
        )
        refreshBalance()
        refreshWeek()
        refreshName()
        round.assets.registerOnBalanceChanged {
            refreshBalance()
        }
        Move.registerOnNext {
            refreshWeek()
        }
        return binding.root

    }

    private fun refreshName() {
        binding.textviewName.text = round.name
    }

    private fun refreshWeek() {
        binding.textviewWeek.text = "Week ${Move.move}"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun refreshBalance() {
        binding.textviewBalance.text = round.assets.balance.toMoney()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}