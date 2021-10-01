package com.lgajowy.learning

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.util
import java.util.EmptyStackException

class ExampleSpec extends AnyFlatSpec with should.Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new util.Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.pop() should be(1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new util.Stack[Int]
    a[EmptyStackException] should be thrownBy {
      emptyStack.pop()
    }
  }
}
