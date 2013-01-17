package com.jaskowski.part1

import akka.actor.Actor
import akka.util.Duration
import akka.util.duration._
import akka.actor.ActorRef
import akka.actor.Props
import akka.routing.RoundRobinRouter
import akka.actor.ActorSystem
import akka.actor.actorRef2Scala

import common._

sealed trait MasterMessage
case class Approximation(value: Double) extends MasterMessage
case object Calculate extends MasterMessage

class Master(listener: ActorRef, worker: ActorRef) extends Actor
   {

  var sum: Double = _
  var nOfResultsCollectedSoFar: Int = _
  val nOfWorkers: Int = 10

  def receive: PartialFunction[Any, Unit] = {
    case Calculate =>      
      for (i <- 0 until nOfWorkers) doWork(i * 100, 100)
    case Result(value) =>
      sum += value
      nOfResultsCollectedSoFar += 1
      println (nOfResultsCollectedSoFar)
      if (nOfResultsCollectedSoFar == nOfWorkers) {
        println("stop")
      }
  }
  
  /**
   * execute the calculation on worker!
   *  
   */
  def doWork(start:Int, nrOfElements:Int) = this receive Result(1)
    

}

class Listener extends Actor {
  def receive = {
    case Approximation(value) =>
      println("\n\tMysterious approximation: \t\t%s\n\t".format(value))
      context.system.shutdown()
  }
}

object MysteriousApp extends App {

  calculate(nrOfWorkers = 2, nrOfElements = 10000, nrOfMessages = 10000)

  // actors and messages ...

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
    // Create an Akka system
    val system = ActorSystem("MysteriousSystem")

    // create the result listener, which will print the result and shutdown the system
    val listener = system.actorOf(Props[Listener], name = "listener")
    val worker   = system.actorOf(Props[Worker], name = "worker")

    // create the master
    val master = system.actorOf(Props(new Master(worker, listener)), name = "master")
//
    // start the calculation
    master ! Calculate

  }
}