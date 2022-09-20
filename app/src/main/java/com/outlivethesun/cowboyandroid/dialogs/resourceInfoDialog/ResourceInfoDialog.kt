package com.outlivethesun.cowboyandroid.dialogs.resourceInfoDialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.outlivethesun.cowboyandroid.R
import com.outlivethesun.cowboyandroid.assets.IAsset
import com.outlivethesun.cowboyandroid.resources.IResource
import com.outlivethesun.cowboyandroid.unit.UNIT_CURRENCY
import com.outlivethesun.cowboyandroid.unit.UNIT_LAND

class ResourceInfoDialog<T : IResource>(private val asset: IAsset<T>) : DialogFragment() {
    private lateinit var editTextDescription: EditText
    private lateinit var textviewNeededLand: TextView
    private lateinit var textviewMinPrice: TextView
    private lateinit var textviewMaxPrice: TextView
    private lateinit var imageViewIcon: ImageView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_resource_info, null)
        editTextDescription = view.findViewById(R.id.resource_info_description)
        textviewNeededLand = view.findViewById(R.id.resource_info_needed_land)
        textviewMinPrice = view.findViewById(R.id.resource_info_min_price)
        textviewMaxPrice = view.findViewById(R.id.resource_info_max_price)
        imageViewIcon = view.findViewById(R.id.resource_info_imageview_icon)

        refreshDescription()
        refreshProperties()

        editTextDescription.isEnabled = false
        val dialog = AlertDialog.Builder(activity).setView(view).setTitle(asset.resource.name)
            .setNegativeButton(getString(R.string.close)) { _, _ ->

            }.create()
        return dialog
    }

    private fun refreshProperties() {
        textviewNeededLand.text = "Needed land: ${asset.resource.neededLand} $UNIT_LAND"
        textviewMinPrice.text = "Min price: ${asset.resource.minPrice} $UNIT_CURRENCY"
        textviewMaxPrice.text = "Max price: ${asset.resource.maxPrice} $UNIT_CURRENCY"
        imageViewIcon.setImageResource(asset.resource.icon)
    }

    private fun refreshDescription() {
        editTextDescription.setText(asset.resource.description)
    }
}