package com.example.student.finalproject.models

import com.example.student.finalproject.controllers.HistoryManager
import java.util.*
import kotlin.properties.Delegates

// model singleton
class AppModel : HistoryManager() {
    // singleton property
    // TODO: singleton members may become normal members later on
    companion object {
        var undoStack = Stack<Card>()
        var redoStack = Stack<Card>()

        // current Card being edited instance
        var card: Card by Delegates.observable(Card()) { _, _, new ->
            cardUpdated?.invoke(new)
        }
        var cardUpdated: ((Card) -> Unit)? = null
    }

    override fun add(card: Card) {
        redoStack.clear()
        undoStack.push(card)
        println("$card added to undoStack")
        println("Current undoStack: $undoStack")
        println("Current redoStacl: $redoStack")
    }

    override fun undo() {
        card = undoStack.pop()
        redoStack.push(card)
        println("From undo function, current undoStack: $undoStack")
        println("Current redoStack: $redoStack")
    }

    override fun redo() {
        card = redoStack.pop()
        undoStack.push(card)
    }

    override fun canUndo(): Boolean {
        return true
    }

    override fun canRedo(): Boolean {
        return true
    }
}