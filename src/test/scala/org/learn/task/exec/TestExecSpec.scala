package org.learn.task.exec

import org.learn.task.validate.ValidationException
import org.scalatest.{FlatSpec, Matchers}

/**
  * Tests for main execution class.
  * NOTE: not all cases are covered, but a lot more are covered in CheckCommandOrder unit tests.
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
class TestExecSpec extends FlatSpec with Matchers {

  "TestExec" should "produce expected output successfully - HOT weather" in {
    val expectedLine = "Removing PJs, shorts, shirt, sunglasses, sandals, leaving house"
    val inputs = Array("HOT", "8,", "6,", "4,", "2,", "1,", "7")
    val lines = new TestExec(inputs).outputLines
    lines shouldBe expectedLine
  }

  "TestExec" should "produce expected output successfully - cold weather" in {
    val expectedLine = "Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house"
    val inputs = Array("COLD", "8,", "6,", "3,", "4,", "2,", "5,", "1,", "7")
    val lines = new TestExec(inputs).outputLines
    lines shouldBe expectedLine
  }

  "TestExec" should "produce shortened output if duplicated clothes - HOT weather" in {
    val expectedLine = "Removing PJs, shorts, fail"
    val inputs = Array("HOT", "8,", "6,", "6")
    val lines = new TestExec(inputs).outputLines
    lines shouldBe expectedLine
  }

  "TestExec" should "produce shortened output if leaving house not fully clothed" in {
    val expectedLine = "Removing PJs, pants, socks, shirt, hat, jacket, fail"
    val inputs = Array("COLD", "8,", "6,", "3,", "4,", "2,", "5,", "7")
    val lines = new TestExec(inputs).outputLines
    lines shouldBe expectedLine
  }

  "TestExec" should "produce shortened output if not taking off PJ first" in {
    val expectedLine = "fail"
    val inputs = Array("COLD", "6")
    val lines = new TestExec(inputs).outputLines
    lines shouldBe expectedLine
  }

  it should "fail to produce expected output with wrong arguments" in {
    val inputs = Array("test")
    intercept[ValidationException]{
      new TestExec(inputs).outputLines
    }

  }
}
