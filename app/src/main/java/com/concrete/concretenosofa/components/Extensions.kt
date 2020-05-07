package com.concrete.concretenosofa.components

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showOptionsBottomSheet(
    block: BottomSheet.() -> Unit) = (activity as AppCompatActivity).showOptionsBottomSheet(block)


fun AppCompatActivity.showOptionsBottomSheet(
    block: BottomSheet.() -> Unit) = BottomSheet(
    block
).also {
    it.show(supportFragmentManager, it.tag)
}
