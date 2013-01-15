package com.jaskowski

import com.jaskowski.PiPartCalculator
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PiPartCalculatorSuite extends FunSuite  {

  val piPartCalculator = new PiPartCalculator(){};
  
  test("calculates first value (4)") {
    val result = piPartCalculator.calculatePiFor(0, 1);
    
    assert(result === 4);
  }
  
  test("calculates second value (-4/3)") {
    val result = piPartCalculator.calculatePiFor(1, 1);
    
    assert(result === -4./3);
  }
  
  test("calculates fifts value (4/9)") {
    val result = piPartCalculator.calculatePiFor(4, 1);
    
    assert(result === 4./9);
  }
  
  test("calculatesSecondApproximation") {
    val result = piPartCalculator.calculatePiFor(0, 2);
    
    assert(result === 4 * (1. - 1./3));
  }
  
  test("calculatesFifthApproximation") {
    val result = piPartCalculator.calculatePiFor(0, 5);
    
    assert(result === 4. * (1. - 1./3 + 1./5 - 1./7 + 1./9));
  }
}