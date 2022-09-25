package com.outlivethesun.cowboyandroid.dialogs.amountInputDialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.slider.Slider
import com.outlivethesun.cowboyandroid.R
import com.outlivethesun.cowboyandroid.formatter.toAmount
import com.outlivethesun.cowboyandroid.formatter.toMoney
import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.stockMarket.StockMarket

class AmountInputDialog(
    private val title: String,
    private val positiveButtonCaption: String,
    private val amountConverter: IAmountConverter,
    private val resource: IResource,
    private val isNegativeProfit: Boolean
) : DialogFragment() {
    private lateinit var textViewAmountValue: TextView
    private lateinit var textViewPriceValue: TextView
    private lateinit var textViewProfitValue: TextView
    private lateinit var imageViewIcon: ImageView
    private lateinit var textViewRemainingLand: TextView
    private lateinit var slider: Slider
    private var amount: Long = 0
    private var profit: Double = 0.0
    private val eventHandlersOnCancel: MutableList<() -> Unit> = mutableListOf()
    private val eventHandlersOnOkay: MutableList<(value: Long) -> Unit> = mutableListOf()

    fun registerOnCancel(eventHandler: () -> Unit) {
        eventHandlersOnCancel.add(eventHandler)
    }

    fun registerOnOkay(eventHandler: (value: Long) -> Unit) {
        eventHandlersOnOkay.add(eventHandler)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_pick_amount, null)
        textViewAmountValue = view.findViewById(R.id.pick_amount_textview_amount_value)
        textViewPriceValue = view.findViewById(R.id.pick_amount_textview_price_value)
        textViewProfitValue = view.findViewById(R.id.pick_amount_textview_profit_value)
        imageViewIcon = view.findViewById(R.id.pick_amount_imageview_icon)
        textViewRemainingLand = view.findViewById(R.id.pick_amount_textview_remaining_land)
        slider = view.findViewById(R.id.pick_amount_value_slider)

        refreshAmount()
        refreshPrice()
        refreshProfit()
        refreshIcon()
        refreshRemainingLand()

        slider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            amount = amountConverter.convertPercentToAmount(value)
            profit = amount * StockMarket.getResourcePrice(resource)
            refreshAmount()
            refreshProfit()
        })

        val buttonMinus = view.findViewById<Button>(R.id.pick_amount_button_minus)
        buttonMinus.setOnClickListener {
            if (amount > amountConverter.minValue) {
                amount--
                updateSliderFromAmount()
            }
        }

        val buttonPlus = view.findViewById<Button>(R.id.pick_amount_button_plus)
        buttonPlus.setOnClickListener {
            if (amount < amountConverter.maxValue) {
                amount++
                updateSliderFromAmount()
            }
        }

        val buttonMax = view.findViewById<Button>(R.id.pick_amount_button_max)
        buttonMax.setOnClickListener {
            if (amount < amountConverter.maxValue) {
                amount = amountConverter.maxValue
                updateSliderFromAmount()
            }
        }

        return AlertDialog.Builder(activity).setView(view).setTitle(title).setPositiveButton(
            positiveButtonCaption
        ) { _, _ ->
            // only inform event handler in case a value greater 0 was entered
            if (amount != 0L) {
                eventHandlersOnOkay.forEach { eventHandler ->
                    eventHandler.invoke(amount)
                }
            }
        }.setNegativeButton(R.string.cancel) { _, _ ->
            eventHandlersOnCancel.forEach { eventHandler ->
                eventHandler.invoke()
            }
        }.create()
    }

    private fun refreshRemainingLand() {
        if (resource.neededLand == 0.0) {
            textViewRemainingLand.visibility = View.GONE
        } else {
            textViewRemainingLand.text = "Remaining land for ${
                amountConverter.convertLandToAmount().toAmount()
            } ${resource.name}."
        }
    }

    private fun updateSliderFromAmount() {
        slider.value = amountConverter.convertAmountToPercent(amount) / 100
    }

    private fun refreshPrice() {
        textViewPriceValue.text = StockMarket.getResourcePrice(resource).toMoney()
    }

    private fun refreshAmount() {
        textViewAmountValue.text = amount.toAmount()
    }

    private fun refreshProfit() {
        if (!isNegativeProfit || profit == 0.0) {
            textViewProfitValue.text = profit.toMoney()
        } else {
            textViewProfitValue.text = "-${profit.toMoney()}"
        }
    }

    private fun refreshIcon() {
        imageViewIcon.setImageResource(resource.icon)
    }
}