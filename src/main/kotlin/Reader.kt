package main

class Reader(private val ndc: NDC) {

    fun run(): Double {
        val count = ndc.getNdc()
        if(count>App.TARGET_NUMBER){
            computeTime(System.currentTimeMillis())
        }
        return count
    }
}