package com.example.student.finalproject.controllers

import com.example.student.finalproject.models.Card

// TODO: refactor into Command interface
abstract class HistoryManager {
    abstract fun add(card: Card)
    abstract fun redo()
    abstract fun undo()
    abstract fun canUndo(): Boolean
    abstract fun canRedo(): Boolean
}
