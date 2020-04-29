package com.concrete.concretenosofa.extensions

import android.view.View
import com.concrete.concretenosofa.R
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import java.util.*

fun Double.toTempFormat() = this.toString().replace(".0","")

fun Calendar.getSalutation() =
    when {
        this.get(Calendar.HOUR_OF_DAY) in 5..12 -> {
            "Bom Dia"
        }
        this.get(Calendar.HOUR_OF_DAY) < 18 -> {
            "Boa Tarde"
        }
        else -> {
            "Boa Noite"
        }
    }

fun Calendar.getBackgroundColor() =
    when {
        this.get(Calendar.HOUR_OF_DAY) in 5..18 -> {
            R.color.colorBackgroundDay
        }
        else -> {
            R.color.colorBackgroundNight
        }
    }

fun Calendar.getLogStringDate() =
    "${this.get(Calendar.DAY_OF_MONTH)} de ${this.get(Calendar.MONTH).getStringMonth()} de ${this.get(Calendar.YEAR)}"

fun Int.getStringMonth() =
    when(this){
        Calendar.FEBRUARY -> "fevereiro"
        Calendar.MARCH -> "março"
        Calendar.APRIL -> "abril"
        Calendar.MAY -> "maio"
        Calendar.JUNE -> "junho"
        Calendar.JULY -> "julho"
        Calendar.AUGUST -> "agosto"
        Calendar.SEPTEMBER -> "setembro"
        Calendar.OCTOBER -> "outubro"
        Calendar.NOVEMBER -> "novembro"
        Calendar.DECEMBER -> "dezembro"
        else ->  "janeiro"
    }

fun View.fadeIn() {
    this.visible()
    YoYo.with(Techniques.FadeIn).duration(800).playOn(this)
}
fun View.fadeOut() {
    this.hide()
    YoYo.with(Techniques.FadeOut).duration(300).playOn(this)
}
fun View.slideIn(duration: Long) {
    this.visible()
    YoYo.with(Techniques.SlideInLeft).duration(duration).playOn(this)
}
fun View.slideInRight(duration: Long) {
    this.visible()
    YoYo.with(Techniques.SlideInRight).duration(duration).playOn(this)
}
fun View.startMoving(){
    YoYo.with(Techniques.Pulse).duration(3000).onEnd { startMoving() }.playOn(this)
}

fun View.showUp(){
    this.visible()
    YoYo.with(Techniques.Landing).duration(2000).onEnd { startMoving() }.playOn(this)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.hide(){
    this.visibility = View.GONE
}
