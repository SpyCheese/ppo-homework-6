package hw6

import hw6.tokens.*
import junit.framework.TestCase
import kotlin.test.assertFailsWith

class EvalVisitorTest : TestCase() {
  fun testNumber() {
    assertEquals(123.0, eval(listOf(NumberToken(123.0))))
  }

  fun testOperators() {
    assertEquals(15.0, eval(listOf(NumberToken(10.0), NumberToken(5.0), OperatorPlus)))
    assertEquals(5.0, eval(listOf(NumberToken(10.0), NumberToken(5.0), OperatorMinus)))
    assertEquals(50.0, eval(listOf(NumberToken(10.0), NumberToken(5.0), OperatorMul)))
    assertEquals(2.0, eval(listOf(NumberToken(10.0), NumberToken(5.0), OperatorDiv)))
  }

  fun testExpression() {
    assertEquals(40.0, eval(listOf(
      NumberToken(10.0),
      NumberToken(7.0),
      NumberToken(2.0),
      OperatorMinus,
      NumberToken(6.0),
      OperatorMul,
      OperatorPlus
    )))
  }

  fun testEmpty() {
    assertFailsWith<RuntimeException> {
      eval(listOf())
    }
  }

  fun testExtraTokens() {
    assertFailsWith<RuntimeException> {
      eval(listOf(NumberToken(1.0), NumberToken(2.0), NumberToken(3.0), OperatorPlus))
    }
  }

  fun testNotEnoughTokens() {
    assertFailsWith<RuntimeException> {
      eval(listOf(NumberToken(1.0), OperatorPlus))
    }
  }

  companion object {
    private fun eval(tokens: List<Token>): Double {
      val evaluator = EvalVisitor()
      tokens.forEach { it.accept(evaluator) }
      return evaluator.getResult()
    }
  }
}