package com.example.student.finalproject.controllers

import android.content.Context
import android.view.View
import android.widget.EditText
import com.example.student.finalproject.R
import com.example.student.finalproject.models.AppModel
import com.example.student.finalproject.models.Card
import com.example.student.finalproject.toast

class UndoRedoController(private var context: Context, private var model: AppModel, private var nameEdit: EditText,
                         private var descEdit: EditText, private var costEdit: EditText) : View.OnClickListener {
    init {
        // set action for when a card gets updated in the model
        AppModel.cardUpdated = {
            // update view
            println("Undo - Current Card Shown $it")
            updateCurrentCardView(it)
        }
    }

    private fun updateCurrentCardView(card: Card) {
        // destructuring, not really necessary but wanted to use it
        val (attack, health, name, description, cost, className, rarity, imagePath) = card
        nameEdit.setText(name)
        descEdit.setText(description)
        costEdit.setText(cost.toString())
    }

    // handle click
    override fun onClick(button: View?) {
        context.toast("Clicked")
        when (button!!.id) {
            R.id.undoBtn -> {
                if (model.canUndo()) {
                    model.undo()
                    context.toast("Undo Success")
                } else {
                    context.toast("Undo Failed")
                    // disable button
                }
            }
            R.id.redoBtn -> {
                if (model.canRedo()) {
                    model.redo()
                } else {
                    context.toast("Redo Failed")
                    // disable button
                }
            }
            else -> false
        }
    }
}