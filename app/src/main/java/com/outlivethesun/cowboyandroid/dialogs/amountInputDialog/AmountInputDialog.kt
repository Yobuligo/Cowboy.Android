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
import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.stockMarket.StockMarket
import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY

class AmountInputDialog(
    private val title: String,
    private val positiveButtonCaption: String,
    private val valueConverter: IAmountConverter,
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
        textViewAmountValue = view.findViewById(R.id.textview_amount_value)
        textViewPriceValue = view.findViewById(R.id.textview_price_value)
        textViewProfitValue = view.findViewById(R.id.textview_profit_value)
        imageViewIcon = view.findViewById(R.id.imageview_icon)
        textViewRemainingLand = view.findViewById(R.id.textview_remaining_land)
        slider = view.findViewById(R.id.pick_value_slider)

        refreshAmount()
        refreshPrice()
        refreshProfit()
        refreshIcon()
        refreshRemainingLand()

        slider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            amount = valueConverter.convertPercentToAmount(value)
            profit = amount * StockMarket.getResourcePrice(resource)
            refreshAmount()
            refreshProfit()
        })

        val buttonMinus = view.findViewById<Button>(R.id.pick_value_button_minus)
        buttonMinus.setOnClickListener {
            if (amount > valueConverter.minValue) {
                amount--
                updateSliderFromAmount()
            }
        }

        val buttonPlus = view.findViewById<Button>(R.id.pick_value_button_plus)
        buttonPlus.setOnClickListener {
            if (amount < valueConverter.maxValue) {
                amount++
                updateSliderFromAmount()
            }
        }

        val buttonMax = view.findViewById<Button>(R.id.pick_value_button_max)
        buttonMax.setOnClickListener {
            if (amount < valueConverter.maxValue) {
                amount = valueConverter.maxValue
                updateSliderFromAmount()
            }
        }

        return AlertDialog.Builder(activity).setView(view).setTitle(title).setPositiveButton(
            positiveButtonCaption
        ) { _, _ ->
            val value = textViewAmountValue.text.toString().toLong()
            // only inform event handler in case a value greater 0 was entered
            if (value != 0L) {
                eventHandlersOnOkay.forEach { eventHandler ->
                    eventHandler.invoke(value)
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
            textViewRemainingLand.text =
                "Remaining land for ${valueConverter.convertLandToAmount()} ${resource.name}."
        }
    }

    private fun updateSliderFromAmount() {
        slider.value = valueConverter.convertAmountToPercent(amount) / 100
    }

    private fun refreshPrice() {
        textViewPriceValue.text = "${StockMarket.getResourcePrice(resource)} ${UNIT_CURRENCY}"
    }

    private fun refreshAmount() {
        textViewAmountValue.text = amount.toString()
    }

    private fun refreshProfit() {
        if (!isNegativeProfit || profit == 0.0) {
            textViewProfitValue.text = "$profit ${UNIT_CURRENCY}"
        } else {
            textViewProfitValue.text = "-$profit ${UNIT_CURRENCY}"
        }
    }

    private fun refreshIcon() {
        imageViewIcon.setImageResource(resource.icon)
    }
}