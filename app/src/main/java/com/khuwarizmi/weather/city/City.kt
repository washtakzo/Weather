package com.khuwarizmi.weather.city

data class City(var id : Int,
                val name : String) {

    constructor(name : String) : this(-1,name)

}