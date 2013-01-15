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

@RunWith(classOf[JUnitRunner])
class WorkerSuite extends FunSuite {
  implicit val system = ActorSystem("MyActorSystem")
  implicit val timeout = Timeout(5 seconds)
  
  val worker: ActorRef = TestActorRef(new Worker)
  
  test("calculates first value (4)") {

    //when
    val future: Future[Any] = worker ? Work (0, 1)
    
    //then
    assert( awaitResult(future).value === 4.);
  }
  
  test("calculates second value (-1)") {

    //when
    val future: Future[Any] = worker ? Work (1, 1)
    
    //then
    assert( awaitResult(future).value === -4./3);
  }
  
  
  def awaitResult(future: Future[Any]) = Await.result(future, timeout.duration).asInstanceOf[Result]
  
}