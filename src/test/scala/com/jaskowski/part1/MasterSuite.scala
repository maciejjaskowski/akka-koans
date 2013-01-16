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
import akka.testkit.TestProbe
import akka.testkit.TestActor.AutoPilot

@RunWith(classOf[JUnitRunner])
class MasterSuite extends FunSuite {
  implicit val system = ActorSystem("MyActorSystem")
  implicit val timeout = Timeout(5 seconds)

  val listener: TestProbe = TestProbe();
  val worker: TestProbe = TestProbe();
  val master: ActorRef = TestActorRef(new Master(listener.ref, worker.ref))

  /**
   * use listener in an appropriate place
   * and unignore the other test
   */
  test("tells listener the result of approximation") {
	
    //when
    master ! Calculate

    // then
    val approximation = listener.expectMsgClass(classOf[Approximation])
    assert(approximation.value === 10)

  }

  /**
   * use worker in "doWork" method
   */
  test("uses worker to calculate the result") {

    worker.setAutoPilot(autoPilotReturning(2))
    //when
    master ! Calculate

    // then
    val approximation = listener.expectMsgClass(classOf[Approximation])
    assert(approximation.value === 20)

  }

  def autoPilotReturning(value: Int) = new AutoPilot {
    def run(sender: ActorRef, msg: Any): Option[AutoPilot] =
      msg match {
        case "stop" => None
        case Work(_,_) => sender ! Result(value); Some(this)
      }

  }

  def awaitResult(future: Future[Any]) = Await.result(future, timeout.duration).asInstanceOf[Result]

}