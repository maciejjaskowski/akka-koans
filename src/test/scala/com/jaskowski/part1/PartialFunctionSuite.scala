package com.jaskowski.part1

import akka.actor.ActorSystem
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestActorRef
import akka.dispatch.Future
import akka.dispatch.Await
import akka.util.duration._
import akka.util.Timeout
import org.scalatest.FunSuite
import akka.actor.ActorRef
import akka.pattern.ask

import common._

@RunWith(classOf[JUnitRunner])
class PartialFunctionSuite extends FunSuite {

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + " is even"
  }
  
  def isOdd: PartialFunction[Int, String] = ???
  
  def extendedFunction(i:Int): String = ???

  test("4 is even") {
    val result = isEven(4)

    assert (result === "4 is even")
  }
  
  test("5 is not even") {
    intercept[MatchError] {
    	isEven(5)
    }
  }
  
  test("5 is odd") {
    val result = isOdd(5)
    
    assert (result === "5 is odd")
  }
  
  test("6 is not odd") {
    intercept[MatchError] {
    	isOdd(6)
    }
  }
  
  test("extendended function handles both even and odd numbers") {
    assert(extendedFunction(0) === "0 is even")
    assert(extendedFunction(2) === "2 is even")
    assert(extendedFunction(5) === "5 is odd")
    assert(extendedFunction(9) === "9 is odd")
    assert(extendedFunction(-1) === "-1 is odd")
  }

}