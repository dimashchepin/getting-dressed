package org.learn.task.model

import org.learn.task.common.CommonFunctions._
/**
  * Text output for commands.
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@here.com>
  */
object CommandText {

  val StopCommandText = Option("fail")

  val outputsByTemp = Map(
    HotTemperatureMode -> Map(
      StopCommand -> StopCommandText,
      footWearCommand -> Option("sandals"),
      headWearCommand -> Option("sunglasses"),
      3 -> None,
      4 -> CommandShirt,
      5 -> None,
      6 -> Option("shorts"),
      7 -> CommandLeaveHouse,
      8 -> CommandPajamas
    ),
    ColdTemperatureMode -> Map(
      StopCommand -> StopCommandText,
      footWearCommand -> Option("boots"),
      headWearCommand -> Option("hat"),
      3 -> CommandSocks,
      4 -> CommandShirt,
      5 -> CommandJacket,
      6 -> CommandPants,
      7 -> CommandLeaveHouse,
      8 -> CommandPajamas
    )
  )
}
