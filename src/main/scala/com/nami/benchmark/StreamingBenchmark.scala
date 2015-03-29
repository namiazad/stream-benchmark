package com.nami.benchmark

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode}

object StreamingBenchmark {
  val source = 1 to 50000000
  def lazyData = source.toStream
  def arrayData = source.toArray
}

class StreamingBenchmark {
  import StreamingBenchmark._
  def process(xs: => Traversable[Int]): Long = xs.map(_ + 10).filter(_ % 3 == 0).map(_ * 2).sum

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  def forLoop(): Long = forLoop(arrayData)

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  def streaming(): Long = streaming(lazyData)

  @Benchmark
  @BenchmarkMode(Array(Mode.Throughput))
  def strictProcessing(): Long = collection(arrayData)

  def forLoop(xs: Array[Int]): Long = {
    val length = xs.length
    var i = 0

    var result = 0L
    while (i < length) {
      val plus10 = xs(i) + 10
      if (plus10 % 3 == 0) {
        result = result + (plus10 * 2)
      }
      i = i + 1
    }

    result
  }

  def streaming(xs: => Stream[Int]): Long = process(xs)

  def collection(xs: Seq[Int]): Long = process(xs)
}
