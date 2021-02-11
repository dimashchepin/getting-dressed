package org.learn.task.exec

import org.learn.task.model.{CommandText, ValidatedInputs}
import org.learn.task.order.CheckCommandOrder
import org.learn.task.validate.CheckInputs

/**
  *  Run application.
  *
  * @author Dmitriy Shternberg <dimashchepin@gmail.com>
  */
object TestExec extends App {

  // NOTE: since specification calls for simple output, using println...
  println(new TestExec(args).outputLines)
}

/**
  * Process inputs and display output or error according to rules.
  *
  * @param applicationInputs command-line inputs
  */
class TestExec(applicationInputs: Array[String]) {

  //NOTE: not really necessary, but i thought it will be helpful to validate first....
  val validatedInputs: ValidatedInputs = CheckInputs(applicationInputs.toList)

  /**
    * Display command output.
    *
    * @return output string
    */
  def outputLines: String = {
    // check all rules!
    val orderedCommands = CheckCommandOrder(validatedInputs.temperatureType, validatedInputs.numericCommands).finalCommands

    // transform from numeric to text commands
    convertCommandsToText(orderedCommands).mkString(", ")
  }

  private[exec] def convertCommandsToText(numericCommands: List[Int]): List[String] = {
    numericCommands.map(numCmd => CommandText.outputsByTemp(validatedInputs.temperatureType)(numCmd).get)
  }
}
