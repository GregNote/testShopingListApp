package pl.gregnote.testshopingapp.util

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog

fun getDialogWithEdit(context: Context, infoText: String, callback: (String) -> Unit) {
    val dialog: AlertDialog = AlertDialog.Builder(context).create()
    val editText = EditText(context).apply {
        hint = infoText
    }
    val dialogView: View = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        addView(
            editText,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        addView(
            Button(context).apply {
                text = "OK"
                setOnClickListener {
                    editText.text.toString().also {
                        if (it.isNotEmpty()) callback(it)
                    }
                    dialog.dismiss()
                }
            },
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
    }
    dialog.setView(dialogView)
    dialog.show()
}