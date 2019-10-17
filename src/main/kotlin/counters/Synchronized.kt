package main.counters

import main.NDC

class Synchronized: NDC {

    private var counter: Double = 0.0;

    private val lock = Any()

    override fun getNdc(): Double {
        synchronized(lock){
            return counter
        }
    }

    override fun resetNdc(resetValue: Double) {
        synchronized(lock){
            counter = resetValue
        }
    }

    override fun updateNdc(value: Double) {
        synchronized(lock){
            counter+= value
        }
    }

    override fun updateAndGet(value: Double): Double {
        synchronized(lock){
            counter+= value
            return counter
        }
    }

}