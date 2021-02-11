package org.learn.task.model

/**
  * Container/Processor for input values after validation.
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
case class ValidatedInputs(temperatureType: String, numericCommands: List[Int])
