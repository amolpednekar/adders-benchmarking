package main

class ReaderWriter(private val ndc: NDC) {

    fun run(value: Double): Double {
        val count = ndc.updateAndGet(value)
        if(count>App.TARGET_NUMBER){
            computeTime(System.currentTimeMillis())
        }
        return count
    }
}