package main

interface NDC {
    fun getNdc(): Double
    fun resetNdc(resetValue: Double)
    fun updateNdc(value: Double)
}