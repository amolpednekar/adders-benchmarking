package main.counters

import com.google.common.util.concurrent.AtomicDouble
import main.NDC

class Atomic: NDC {

    private val counter = AtomicDouble()

    override fun getNdc(): Double {
        return counter.get()
    }

    override fun resetNdc(resetValue: Double) {
        counter.set(resetValue)
    }

    override fun updateNdc(value: Double) {
        counter.addAndGet(value)
    }

}