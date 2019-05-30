package com.example.practiceproject2

interface ItemTouchHelperListener {
    fun onMove(oldPosition: Int, newPosition: Int)
    fun swipe(position: Int, direction: Int)
}