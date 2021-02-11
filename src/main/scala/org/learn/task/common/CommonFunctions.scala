package org.learn.task.common

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Try}

/**
  * Common functions
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
object CommonFunctions extends LazyLogging {

  val HotTemperatureMode = "HOT"
  val ColdTemperatureMode = "COLD"
  val StopCommand = 0
  val footWearCommand = 1
  val headWearCommand = 2

  val CommandPajamas = Option("Removing PJs")
  val CommandLeaveHouse = Option("leaving house")
  val CommandSocks = Option("socks")
  val CommandPants = Option("pants")
  val CommandShirt = Option("shirt")
  val CommandJacket = Option("jacket")

  /**
    * Catching errors.
    *
    * @param codeBlock commands to be monitored
    * @tparam A return type
    * @return result of code block
    */
  def logTryFailure[A](codeBlock: => A): Try[A] = {
    Try(codeBlock) recoverWith {
      case e: Throwable => Failure(e)
    }
  }

}
