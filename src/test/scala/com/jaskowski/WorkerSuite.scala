package com.jaskowski

import akka.actor.ActorSystem
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import akka.testkit.TestActorRef
import akka.actor.Status.Success
import akka.pattern.ask
import akka.dispatch.Future
import akka.dispatch.Await
import akka.util.duration._
import akka.util.Timeout
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class WorkerSuite extends FunSuite {
  implicit val system = ActorSystem("MyActorSystem")
  implicit val timeout = Timeout(5 seconds)
  
  test("calculates first value (4)") {
	
    val worker = TestActorRef(new Worker)
    
    val future = worker ? Work (0, 1)
    
    val result = Await.result(future, timeout.duration).asInstanceOf[Result]
    assert( result.value == 4.);
    
  }
  
}