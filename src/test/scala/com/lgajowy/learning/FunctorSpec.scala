package com.lgajowy.learning

import cats.Functor
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class FunctorSpec extends AnyFunSuite with should.Matchers {

  /** NOTE: functor can "lift" a pure function A => B into an effectful function F[A] => F[B]! */
  test("A functor type class can provide a map() function to any type") {
    Functor[Option].map(Some(2))(x => x * x) should be(Some(4))

    // But I can't do this... because Either has [A, B] (2 type parameters)
    //Functor[Either].map(Some(2))(x => x * x) should be(Some(4))
    // For the above I will need a Bifunctor!
  }

  test("Identity") {
    Functor[Option].map(Some(1))(x => x) == Some(1) should be(true)
  }

  test("Functors compose") {

    def f(x: Int): String = x.toString
    def g(x: String): Boolean = x == "foo"

    val h: Int => Boolean = f _ andThen g
    val list = List(1, 2, 3)
    val res1: Seq[Boolean] = Functor[List].map(list)(f).map(g)
    val res2: Seq[Boolean] = Functor[List].map(list)(h)
    res1 should equal(res2)
  }

  test("Functors really compose!") {
    val x = List(Some(1), Some(2), None)

    val composedFunctor = Functor[List].compose[Option]

    val expectedResult = List(Some(2), Some(4), None)
    composedFunctor.map(x)((element: Int) => element * 2) should be(expectedResult)

    // It's like doing:
    Functor[List].map(x)(element => element.map(_ * 2)) should be(expectedResult)

  }

  trait MyFunctor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

}
