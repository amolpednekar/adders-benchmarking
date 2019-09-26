package main

import main.NDC

class Writer(private val ndc: NDC) {

    fun run(value: Double){
        ndc.updateNdc(value)
    }

}