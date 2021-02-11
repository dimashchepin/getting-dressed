package org.learn.task.order

import org.learn.task.model.CommandText
import org.learn.task.common.CommonFunctions._

/**
  * Output list of commands after applying rules.
  *
  * @author Dmitriy Shternberg <dimashchepin@gmail.com>
  */
object CheckCommandOrder {
  def apply(temperatureMode: String, initialCommands: List[Int]): CheckCommandOrder = new CheckCommandOrder(temperatureMode, initialCommands)
}

class CheckCommandOrder(temperatureMode: String, initialCommands: List[Int]) {

  val correctCommands: Map[Int, Option[String]] = CommandText.outputsByTemp(temperatureMode).filter(_._2.isDefined).filter(_._1 != 0)
  val commandsByName: Map[Option[String], Int] = for ((k,v) <- correctCommands) yield (v, k)
  val clothingCommands: Set[Int] = correctCommands.filterNot(_._1 == commandsByName(CommandLeaveHouse)).keys.toSet

  /**
    * Check each command with rules - return it as is OR return zero (stop command).
    *
    * @param newList  new command list to build
    * @param sequence place of command in the list
    * @param oldList remaining old command list to check
    * @return resulting or intermediate list of commands
    */
  private[order] def validCommandOrFail(newList: List[Int], sequence: Int, oldList: List[Int]): List[Int] = {
    // list rules
    def commandOutOfScope(cmd: Int) = ! correctCommands.contains(cmd)
    def firstCommandNotPajamas(cmd: Int) = newList.isEmpty && cmd != commandsByName(CommandPajamas)
    def commandIsRepeated(cmd: Int) = newList.contains(cmd)
    def lastCommandNotLeaving(cmd: Int) = oldList.tail.isEmpty && cmd != commandsByName(CommandLeaveHouse)
    def footwearBeforeSocks(cmd: Int) = cmd == footWearCommand && oldList.tail.contains( commandsByName(CommandSocks) )
    def footwearBeforePants(cmd: Int) = cmd == footWearCommand && oldList.tail.contains( commandsByName(CommandPants) )
    def headwearBeforeShirt(cmd: Int) = cmd == headWearCommand && oldList.contains( commandsByName(CommandShirt) )
    def jacketBeforeShirt(cmd: Int) = cmd == commandsByName(CommandJacket) && oldList.contains( commandsByName(CommandShirt) )
    def leavingHouseNotFullyDressed(cmd: Int) = cmd == commandsByName(CommandLeaveHouse) && ! clothingCommands.forall(newList.contains(_))

    // apply rules
    val cmdToAdd: Int = {
      oldList.head match {
        case x if commandOutOfScope(x) => StopCommand
        case x if firstCommandNotPajamas(x) || commandIsRepeated(x) || lastCommandNotLeaving(x) || headwearBeforeShirt(x) => StopCommand
        case x if temperatureMode == ColdTemperatureMode && (footwearBeforePants(x) || footwearBeforeSocks(x) || jacketBeforeShirt(x)) => StopCommand
        case x if leavingHouseNotFullyDressed(x) => StopCommand
        case _ => oldList.head
      }
    }

    if (oldList.tail.isEmpty) newList ::: List(cmdToAdd)
    else if (cmdToAdd == StopCommand) newList ::: List(cmdToAdd)
    else validCommandOrFail(newList ::: List(cmdToAdd), sequence + 1, oldList.tail)
  }

  /**
    * Output final list of commands after checking order.
    *
    * @return list cf commands
    */
  def finalCommands: List[Int] = {
    // recurse through list of commands to check order
    validCommandOrFail(List(), 0, initialCommands)
  }
}
