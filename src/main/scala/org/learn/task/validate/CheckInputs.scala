package org.learn.task.validate

import org.learn.task.common.CommonFunctions.{logTryFailure, HotTemperatureMode, ColdTemperatureMode }
import org.learn.task.model.ValidatedInputs
import org.learn.task.order.CheckCommandOrder

import scala.util.{Failure, Success}

/**
  * Initial validation of command-line inputs here.
  * NOTE: this was not in specified tasks, but I assumed we don't want invalid inputs...
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
object CheckInputs {
  val AllowedNumericCommands = List(1,2,3,4,5,6,7,8)
  val allowedTemperatureModes = List(HotTemperatureMode, ColdTemperatureMode)

  def apply(applicationInputs: List[String]): ValidatedInputs = {
    applicationInputs match {
      case List() => throw ValidationException("Argument list cannot be empty")
      case tempMode::numerics => processArgs(tempMode, numerics)
    }
  }

  private def processArgs(inputTempMode: String, numerics: List[String]) = {
    val temperatureMode = validateTempMode(inputTempMode)
    val initialNumericCommands = validateNumericCommandsList(numerics)

    ValidatedInputs(temperatureMode, initialNumericCommands)
  }

  private def validateTempMode(tempMode: String): String = {
    tempMode match {
      case(x) if allowedTemperatureModes.contains(x) => x
      case _ => throw ValidationException(s"Temperature mode $tempMode is not one of allowed values: $allowedTemperatureModes")
    }
  }

  private def validateNumericCommandsList(numerics: List[String]): List[Int] = {
    numerics match {
      case List() => throw ValidationException("Numeric command list not specified!")
      case commands if commands.reverse.head.endsWith(",") => throw ValidationException("Numeric command list has trailing comma!")
      case commands if ! commands.init.forall(_.endsWith(",")) => throw ValidationException("Numeric commands not separated by commas!")
      case _ => createInputListOfCommands(numerics)
    }
  }

  private def createInputListOfCommands(commands: List[String]): List[Int] = {
    logTryFailure{
      commands.map(_.replace(",", "").toInt)
    } match {
      case Success(numericCommands) => numericCommands
      case Failure(ex) => throw ValidationException(s"Error creating list of numeric commands: ${ex.getMessage}", ex)
    }
  }
}
