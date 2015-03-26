package com.nami.fpinscala

object MemoryUsage extends App {
  import StreamingBenchmark._

  (1 to 20).foreach { x => Thread.sleep(1000); println (s"$x seconds")}

  def measure(): Unit = {
    new StreamingBenchmark().forLoop(strictData)
    println("ForLoop Done!")

    System.gc()
    Thread.sleep(5000)

    new StreamingBenchmark().streaming(lazyData)
    println("Streaming Done!")

    System.gc()
    Thread.sleep(5000)

    new StreamingBenchmark().collection(strictData)
    println("Collection Done!")

    System.gc()
    Thread.sleep(5000)
  }

  measure()

  System.gc()

  Thread.sleep(50000)

}

//TODO: Computing memory usage
//If the streaming memory usage is less than normal one we can formulate the problem as follow:
//So Streaming is more efficient in memory usage because of eliminating intermediate lists so shall we use them more often
//The answer is No: the throughput is much more less.