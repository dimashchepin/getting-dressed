package org.learn.task.validate

/**
  * Custom command-line argument validation exception.
  *
  * @author Dmitriy Shternberg <dmitriy.shternberg@gmail.com>
  */
case class ValidationException(errorMessage: String, inheritedException: Throwable = null) extends RuntimeException(errorMessage, inheritedException)
