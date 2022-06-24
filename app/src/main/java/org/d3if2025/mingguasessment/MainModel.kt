package org.d3if2025.mingguasessment

import android.icu.text.CaseMap


data class MainModel( val result: ArrayList<Result>){
    data class Result ( val id: Int, val title: String, val image: String)
}