package main

import main.App.Companion.addersMap
import main.App.Companion.counter
import main.App.Companion.executorService
import main.App.Companion.numberOfIncrements
import main.App.Companion.numberOfThreads
import main.App.Companion.startTime
import main.App.Companion.sum
import main.counters.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

class App {
    companion object {
        var COUNTER = CounterTypes.ATOMIC.toString()
        val addersMap = ConcurrentHashMap<String, NDC>()

        const val TARGET_NUMBER = 999999
        const val numberOfThreads = 20
        const val numberOfIncrements = 50000

        var startTime: Long = 0
        var sum = 0L
        var counter = 0
        val executorService: ExecutorService = Executors.newFixedThreadPool(50)
    }
}

fun main(){
    CounterTypes.values().iterator().forEach {
        println("\n\nRunning counter test for $it")
        counterTest(it.name)
    }

    executorService.shutdownNow()
}

fun counterTest(counter: String) {

    // Build NDC Map from nodes list
    val lineList = readFile()

    lineList.forEach {
        addersMap[it] = getCounter(counter)
    }

    val incrementAction = {
        addersMap.iterator().forEach {
            IntStream
                .range(0, numberOfIncrements)
                .forEach { _ ->
//                    Writer(it.value).run(1.0)
//                    Reader(it.value).run()
                    ReaderWriter(it.value).run(1.0)
                }
        }
    }

    startTime = System.currentTimeMillis();
    for (i in 0 until numberOfThreads) {
        executorService.execute(incrementAction)
    }

    executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);

    addersMap.iterator().forEach {
        println("Expected ${numberOfIncrements * numberOfThreads}")
        println("Actual ${it.key} ${it.value.getNdc()}")
    }

}

fun computeTime(endTime: Long) {
    val totalTime = endTime - startTime
    sum+= totalTime
    println("Total time: $totalTime")
    counter++
    val average = sum/ addersMap.size
    if(counter%addersMap.size==0) {
        println("Average: $average")
        sum = 0
    }
}

fun getCounter(counter: String): NDC {

    return when (CounterTypes.valueOf(counter)) {
        CounterTypes.ADDER -> Adder()
        CounterTypes.ATOMIC -> Atomic()
        CounterTypes.DIRTY -> Dirty()
        CounterTypes.SYNCHRONIZED -> Synchronized()
    }

}