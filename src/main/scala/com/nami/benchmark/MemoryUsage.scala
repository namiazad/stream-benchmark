package com.nami.benchmark

object MemoryUsage extends App {
  import StreamingBenchmark._

  val Delay = 5000

  (1 to 20).foreach { x => Thread.sleep(1000); println (s"$x seconds")}

  def gcWithDelay(): Unit = {
    System.gc()
    Thread.sleep(Delay)
  }

  def measure(): Unit = {
    new StreamingBenchmark().streaming(lazyData)
    println("Streaming Done!")

    gcWithDelay()

    new StreamingBenchmark().forLoop(arrayData)
    println("ForLoop Done!")

    gcWithDelay()

    new StreamingBenchmark().collection(arrayData)
    println("Collection Done!")

    gcWithDelay()

  }

  (1 to 3).foreach { _ =>
    measure()

    Thread.sleep(Delay)
  }
}
