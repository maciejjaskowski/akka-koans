package com.jaskowski.part1

import akka.actor.Actor
import akka.util.Duration
import akka.util.duration._
import akka.actor.ActorRef
import akka.actor.Props
import akka.routing.RoundRobinRouter
import akka.actor.ActorSystem
import akka.actor.actorRef2Scala

sealed trait WorkerMessage
case class Work(start: Int, nrOfElements: Int) extends WorkerMessage
case class Result(value: Double) extends WorkerMessage

class Worker extends Actor with MysteriousCalculator{

  def receive = {
    case Work(start, nrOfElements) =>
      sender ! Result(calculateValueFor(start, nrOfElements)) // perform the work
  }

}

trait MysteriousCalculator {
  def calculateValueFor(start: Int, nrOfElements: Int): Double = {
    var acc = 0.0
    for (i <- start until (start + nrOfElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }
}

