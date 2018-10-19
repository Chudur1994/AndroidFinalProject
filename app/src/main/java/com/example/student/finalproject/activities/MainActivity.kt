package com.example.student.finalproject

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.student.finalproject.controllers.EditTextController
import com.example.student.finalproject.controllers.UndoRedoController
import com.example.student.finalproject.models.AppModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val model = AppModel()

    private val editTextController = EditTextController(model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: ASK PROF - expose views to undoRedoController, is there a better way of doing this?
        val undoRedoController = UndoRedoController(this, model, nameEdit, descEdit, costEdit)

        // edit field listeners
        editTextController.assignHandlerFor(costEdit, "+")
        editTextController.assignHandlerFor(nameEdit, "Add Name...")
        editTextController.assignHandlerFor(descEdit, "Add Text...")

        // undo/redo listeners
        undoBtn.setOnClickListener(undoRedoController)
        redoBtn.setOnClickListener(undoRedoController)
    }
}

// TODO: ASK PROF - should my extensions be in a separate file?
fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

