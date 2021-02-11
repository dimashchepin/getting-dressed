package org.learn.task.validate

import org.learn.task.model.ValidatedInputs
import org.scalatest.{FlatSpec, Matchers}

/**
  * Tests for CheckInputs class.
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
class CheckInputsSpec extends FlatSpec with Matchers {

  "CheckInputs" should "process correct input values successfully" in {
    val inputs = List("HOT", "8,", "6,", "4,", "2,", "1,", "7")
    val validated = CheckInputs(inputs)
    validated should not be null
    validated.isInstanceOf[ValidatedInputs] shouldBe true
    validated.temperatureType shouldBe inputs.head
    validated.numericCommands shouldBe List(8,6,4,2,1,7)
  }

  it should "fail for empty argument list" in {
    val inputs = List()
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }

  it should "fail if no numeric commands specified" in {
    val inputs = List("HOT")
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }

  it should "fail for incorrect temp mode" in {
    val inputs = List("WRONG")
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }

  it should "fail if arguments after temp mode are not integers between 1 and 8" in {
    val inputs = List("COLD", "TEST,", "ANOTHER_TEST")
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }

  it should "fail if there are no commas between numeric commands" in {
    val inputs = List("COLD", "3", "4")
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }

  it should "fail if there is a trailing comma" in {
    val inputs = List("COLD", "3,", "4,")
    intercept[ValidationException]{
      CheckInputs(inputs)
    }
  }


}
