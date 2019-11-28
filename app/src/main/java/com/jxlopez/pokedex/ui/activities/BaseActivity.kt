package com.jxlopez.pokedex.ui.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected fun generateMessageDialog(
        title: String, message: String, buttonText: String, useOfButtonRequiered: Boolean,
        onclickListener: DialogInterface.OnClickListener?,
        onDismissListener: DialogInterface.OnDismissListener?
    ) {
        val messageDialogBuilder = AlertDialog.Builder(this@BaseActivity)

        messageDialogBuilder.setTitle(title)
        messageDialogBuilder.setMessage(message)
        messageDialogBuilder.setCancelable(!useOfButtonRequiered)

        messageDialogBuilder.setPositiveButton(buttonText, onclickListener)
        messageDialogBuilder.setOnDismissListener(onDismissListener)

        messageDialogBuilder.create().show()
    }

    protected fun generateMessageDialog(
        title: String, message: String, buttonText: String, buttonTextNegative: String, useOfButtonRequiered: Boolean,
        onClickListener: DialogInterface.OnClickListener?,
        onClickListenerNegative: DialogInterface.OnClickListener?
    ) {
        val messageDialogBuilder = AlertDialog.Builder(this@BaseActivity)

        messageDialogBuilder.setTitle(title)
        messageDialogBuilder.setMessage(message)
        messageDialogBuilder.setCancelable(!useOfButtonRequiered)

        messageDialogBuilder.setPositiveButton(buttonText, onClickListener)
        messageDialogBuilder.setNegativeButton(buttonTextNegative, onClickListenerNegative)

        messageDialogBuilder.create().show()
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }
}