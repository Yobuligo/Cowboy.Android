package com.outlivethesun.cowboyandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.outlivethesun.cowboyandroid.RecyclerViewAdapter
import com.outlivethesun.cowboyandroid.assets.AssetsFactory
import com.outlivethesun.cowboyandroid.assets.AssetsFactoryDevMode
import com.outlivethesun.cowboyandroid.assets.IAssets
import com.outlivethesun.cowboyandroid.databinding.FragmentAssetsOverviewBinding
import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.round.Move
import com.outlivethesun.cowboyandroid.round.Round
import com.outlivethesun.cowboyandroid.stockMarket.ProfileViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AssetsOverviewFragment : Fragment() {
    private var _binding: FragmentAssetsOverviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var round: IRound

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssetsOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun refreshName() {
        binding.assetsOverviewTextviewName.text = round.name
    }

    private fun refreshWeek() {
        binding.assetsOverviewTextviewWeek.text = "Week ${Move.move}"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.assetsOverviewRecyclerViewResources.layoutManager =
            LinearLayoutManager(requireContext())

        round = createRound()
        binding.assetsOverviewRecyclerViewResources.adapter =
            RecyclerViewAdapter(requireContext(), round, parentFragmentManager)
        binding.assetsOverviewRecyclerViewResources.addItemDecoration(
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
    }

    private fun refreshBalance() {
        binding.assetsOverviewTextviewBalance.text = round.assets.balance.toMoney()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createRound(): IRound {
        return Round(viewModel.username.ifBlank { viewModel.DEFAULT_USERNAME }, createAssets())
    }

    private fun createAssets(): IAssets {
        return if (viewModel.username.lowercase() == "dev") {
            AssetsFactoryDevMode().create()
        } else {
            AssetsFactory().create()
        }
    }
}