package com.nami.benchmark

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
