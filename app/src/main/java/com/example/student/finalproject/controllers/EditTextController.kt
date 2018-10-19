package com.example.student.finalproject.controllers

import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.EditText
import com.example.student.finalproject.R
import com.example.student.finalproject.models.AppModel

class EditTextController(private var model: AppModel) {
    private var previousText = ""

    // assign listeners for onFocus and onTextChange
    fun assignHandlerFor(editText: EditText, hint: String) {
        // focus listener
        editText.setOnFocusChangeListener { _, hasFocus: Boolean ->
            editText.hint = if (hasFocus) "" else hint

            if (hasFocus) {
                /* save current text to compare after it loses focus */
                previousText = editText.text.toString()
            } else if (!hasFocus && previousText != editText.text.toString()) {
                /* EditText loses focus, there has been text change
                * add the current updated Card instance to the undo Stack */
                model.add(AppModel.card.copy())
            }
        }

        // text listener
        editText.onChange {
            when (editText.id) {
                R.id.costEdit -> AppModel.card.cost = if (it.isEmpty()) 0 else it.toInt()
                R.id.nameEdit -> AppModel.card.name = it
                R.id.descEdit -> {
                    AppModel.card.description = it
                    editText.gravity = Gravity.START
                }
                else -> false
            }
        }
    }
}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}