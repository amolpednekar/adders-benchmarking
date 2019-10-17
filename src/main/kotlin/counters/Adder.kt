package main.counters

import main.NDC
import java.util.concurrent.atomic.DoubleAdder

class Adder() : NDC {
    private val counter = DoubleAdder()

    override fun getNdc(): Double {
        return counter.sum()
    }

    override fun resetNdc(resetValue: Double) {
        counter.reset()
        counter.add(resetValue)
    }

    override fun updateNdc(value: Double) {
        counter.add(value)
    }

    override fun updateAndGet(value: Double): Double {
        counter.add(value)
        return counter.sum()
    }

}