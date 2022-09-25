package com.outlivethesun.cowboyandroid.dialogs.eventInfoDialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.outlivethesun.cowboyandroid.R

class EventInfoDialog(private val title: String, private val text: String) : DialogFragment() {
    private lateinit var textviewText: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_event_info, null)
        textviewText = view.findViewById(R.id.event_info_text)
        textviewText.text = text
        textviewText.isEnabled = false
        return AlertDialog.Builder(requireContext()).setTitle(title).setView(view)
            .setNegativeButton("Close") { _, _ -> }.create()
    }
}