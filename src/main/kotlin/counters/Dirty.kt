package main.counters

import main.NDC

class Dirty: NDC {

    private var counter: Double = 0.0;

    override fun getNdc(): Double {
        return counter
    }

    override fun resetNdc(resetValue: Double) {
        counter = resetValue
    }

    override fun updateNdc(value: Double) {
        counter+= value
    }

}