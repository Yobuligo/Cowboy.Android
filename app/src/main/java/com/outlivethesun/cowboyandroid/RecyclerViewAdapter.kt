package com.outlivethesun.cowboyandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.outlivethesun.cowboyandroid.dialogs.amountInputDialog.AmountConverterBuy
import com.outlivethesun.cowboyandroid.dialogs.amountInputDialog.AmountConverterSell
import com.outlivethesun.cowboyandroid.dialogs.amountInputDialog.AmountInputDialog
import com.outlivethesun.cowboyandroid.dialogs.resourceInfoDialog.ResourceInfoDialog
import com.outlivethesun.cowboyandroid.events.EventTrigger
import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.resources.IProgressable
import com.outlivethesun.cowboyandroid.round.IRound
import com.outlivethesun.cowboyandroid.round.Move
import com.outlivethesun.cowboyandroid.stockMarket.StockMarket


class RecyclerViewAdapter(
    private val context: Context,
    private val round: IRound,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    init {
        round.assets.resources.forEach { asset ->
            asset.registerOnAmountChanged {
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.item_resource, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val asset = round.assets.resources[position]
        holder.icon.setImageResource(asset.resource.icon)
        holder.amount.text = asset.amount.toAmount()
        holder.name.text = asset.resource.name
        holder.price.text = StockMarket.getResourcePrice(asset.resource).toMoney()

        val resource = asset.resource
        if (resource is IProgressable) {
            holder.progressIndicator.visibility = View.VISIBLE
            holder.progressIndicator.progress = resource.calculateProgress(round)
        } else {
            holder.progressIndicator.visibility = View.GONE
        }

        holder.icon.setOnClickListener {
            ResourceInfoDialog(asset).show(fragmentManager, ResourceInfoDialog::class.toString())
        }

        if (!asset.resource.purchasable) {
            holder.buyButton.visibility = View.INVISIBLE
        } else {
            holder.buyButton.visibility = View.VISIBLE
            holder.buyButton.setOnClickListener {
                val amountInputDialog = AmountInputDialog(
                    "Buy ${asset.resource.name}",
                    "Buy",
                    AmountConverterBuy(round, asset.resource),
                    asset.resource,
                    true
                )

                amountInputDialog.registerOnOkay { value ->
                    asset.amount += value
                    round.assets.balance -= (StockMarket.getResourcePrice(asset.resource) * value)
                    moveNext()
                }
                amountInputDialog.show(fragmentManager, AmountInputDialog::class.toString())
            }
        }

        if (asset.amount > 0) {
            holder.sellButton.isEnabled = true
            holder.sellButton.setOnClickListener {
                val amountInputDialog = AmountInputDialog(
                    "Sell ${asset.resource.name}",
                    "Sell",
                    AmountConverterSell(asset),
                    asset.resource,
                    false
                )

                amountInputDialog.registerOnOkay { value ->
                    asset.amount -= value
                    round.assets.balance += (StockMarket.getResourcePrice(asset.resource) * value)
                    moveNext()
                }
                amountInputDialog.show(fragmentManager, AmountInputDialog::class.toString())
            }
        } else {
            holder.sellButton.isEnabled = false
        }
    }

    override fun getItemCount(): Int {
        return round.assets.resources.size
    }

    private fun moveNext() {
        Move.next()
        EventTrigger.trigger(round, fragmentManager)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById<ImageView>(R.id.resource_icon)
        val name = itemView.findViewById<TextView>(R.id.resource_name)
        val amount = itemView.findViewById<TextView>(R.id.resource_amount)
        val price = itemView.findViewById<TextView>(R.id.resource_price)
        val buyButton = itemView.findViewById<Button>(R.id.resource_buy)
        val sellButton = itemView.findViewById<Button>(R.id.resource_sell)
        val progressIndicator =
            itemView.findViewById<LinearProgressIndicator>(R.id.resource_progress_indicator)
    }
}