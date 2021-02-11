package org.learn.task.order

import org.scalatest.{FlatSpec, Matchers}

/**
  * Tests for CheckCommandOrder class.
  *
  * @author Dmitriy Shternberg <dimashchepin@gmail.com>
  */
class CheckCommandOrderSpec extends FlatSpec with Matchers {

  "CheckCommandOrder" should "output commands successfully for correct command order in hot weather" in {
    val tempMode = "HOT"
    val listOfNumerics = List(8, 6, 4, 2, 1, 7)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe listOfNumerics
  }

  "CheckCommandOrder" should "output commands successfully for correct command order in cold weather" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 6, 3, 4, 2, 5, 1, 7)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe listOfNumerics
  }

  it should "output fail and stop processing when it detects incorrect command order - not starting with PJs" in {
    val tempMode = "HOT"
    val listOfNumerics = List(1, 1)
    val expectedList = List(0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - repeating clothes" in {
    val tempMode = "HOT"
    val listOfNumerics = List(8, 6, 6)
    val expectedList = List(8, 6, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - no socks for hot weather" in {
    val tempMode = "HOT"
    val listOfNumerics = List(8, 6, 3, 4, 2, 1, 7)
    val expectedList = List(8, 6, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - no jacket for hot weather" in {
    val tempMode = "HOT"
    val listOfNumerics = List(8, 6, 4, 2, 5, 1, 7)
    val expectedList = List(8, 6, 4, 2, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - socks AFTER footwear" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 6, 1, 4, 2, 5, 3, 7)
    val expectedList = List(8, 6, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - pants AFTER footwear" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 1, 6, 4, 2, 5, 3, 7)
    val expectedList = List(8, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - shirt AFTER headwear" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 6, 3, 2, 4, 5, 1, 7)
    val expectedList = List(8, 6, 3, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - shirt AFTER jacket" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 6, 3, 5, 4, 2, 1, 7)
    val expectedList = List(8, 6, 3, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - leaving house not fully clothed in winter" in {
    val tempMode = "COLD"
    val listOfNumerics = List(8, 6, 3, 4, 2, 5, 7)
    val expectedList = List(8, 6, 3, 4, 2, 5, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }

  it should "output fail and stop processing when it detects incorrect command order - leaving house not fully clothed in summer" in {
    val tempMode = "HOT"
    val listOfNumerics = List(8, 6, 4, 1, 7)
    val expectedList = List(8, 6, 4, 1, 0)
    val outputOrder = CheckCommandOrder(tempMode, listOfNumerics).finalCommands
    outputOrder.isEmpty shouldBe false
    outputOrder shouldBe expectedList
  }
}
