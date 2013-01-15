package com.jaskowski.part1

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MysteriousCalculatorSuite extends FunSuite  {

  val mysteriousCalculator = new MysteriousCalculator(){};
  
  test("calculates first value (4)") {
    val result = mysteriousCalculator.calculateValueFor(0, 1);
    
    assert(result === 4);
  }
  
  test("calculates second value (-4/3)") {
    val result = mysteriousCalculator.calculateValueFor(1, 1);
    
    assert(result === -4./3);
  }
  
  test("calculates fifts value (4/9)") {
    val result = mysteriousCalculator.calculateValueFor(4, 1);
    
    assert(result === 4./9);
  }
  
  test("calculatesSecondApproximation") {
    val result = mysteriousCalculator.calculateValueFor(0, 2);
    
    assert(result === 4 * (1. - 1./3));
  }
}