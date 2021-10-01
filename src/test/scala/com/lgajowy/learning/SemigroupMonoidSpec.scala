package com.lgajowy.learning

import cats.Monoid
import cats.Semigroup
import cats.implicits._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class SemigroupMonoidSpec extends AnyFlatSpec with should.Matchers {

  "A semigroup" should "be able to combine values of various types" in {
    Semigroup[Int].combine(1, 2) should be(3)
    Semigroup[String].combine("foo", "bar") should be("foobar")
  }

  "Semigroup's combine" should "be associative" in {
    1 combine (2 combine 3) should be((1 combine 2) combine 3)
  }

  "A monoid" should "define a sensible unit => empty value" in {
    Monoid[String].empty should be("")
    Monoid[Int].empty should be(0)
  }

  "Combining with empty" should "give the same value" in {
    1 combine Monoid[Int].empty should be(1)
    Monoid[Int].empty combine Monoid[Int].empty should be(Monoid[Int].empty)
  }

  "My custom monoid" should "be able to do the same stuff" in {
    val myMonoid =
      new MyMonoid[Int] {
        override def empty: Int = 0
        override def combine(x: Int, y: Int): Int = x + y
      }

    myMonoid.combine(2, 1) should be(3)
    myMonoid.empty should be(0)
  }

  "As long as I have a monoid, I" should "be able to collapse any sequence" in {

    /** note that I can't do that with a semigroup - it doesn't have an "empty" defined */
    def collapseAnySeq[A: Monoid](list: Seq[A]): A =
      list.foldLeft(Monoid[A].empty)(Monoid[A].combine)

    collapseAnySeq(List(1, 2, 3)) should be(6)
    collapseAnySeq(List("foo", "bar")) should be("foobar")
  }
}

trait MyMonoid[A] {
  // an identity element
  def empty: A
  // an associative operation
  def combine(x: A, y: A): A
}
