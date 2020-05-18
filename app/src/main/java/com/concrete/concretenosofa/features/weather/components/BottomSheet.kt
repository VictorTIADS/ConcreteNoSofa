package com.concrete.concretenosofa.features.weather.components

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concrete.concretenosofa.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

class BottomSheet (private val onBuilt: BottomSheet.() -> Unit) : BottomSheetDialogFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog,container,false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(),
            R.style.BottomSheetTheme
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBuilt()

        ivDialogOptionsCloseButton.setOnClickListener {
            dismiss()
        }
    }

    fun setTitleText(cityName: String){
        bottomSheetTitle.text = getString(R.string.detail_weather_city, cityName)
    }

    fun setFeelsLikeValue(feelsLike: Double){
        bsFeelsLikeValue.text =
            getString(R.string.feels_like_value, feelsLike.toInt())
    }

    fun setHumidityValue(humidity: Double){
        bsHumidityValue.text =
            getString(R.string.humidity_value, humidity.toInt())
    }

    fun setWindSpeedValue(windSpeed: Double){
        bsWindSpeedValue.text =
            getString(R.string.wind_speed_value, windSpeed.toInt())
    }


}