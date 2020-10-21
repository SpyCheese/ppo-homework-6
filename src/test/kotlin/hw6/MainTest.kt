package hw6

import hw6.tokens.NumberToken
import hw6.tokens.OperatorMinus
import hw6.tokens.OperatorMul
import hw6.tokens.OperatorPlus
import junit.framework.TestCase

class MainTest : TestCase() {
  fun testNumber() {
    assertEquals(listOf(NumberToken(50.0)) to 50.0, solve("50"))
  }

  fun testExpression() {
    assertEquals(listOf(
      NumberToken(10.0),
      NumberToken(7.0),
      NumberToken(2.0),
      OperatorMinus,
      NumberToken(6.0),
      OperatorMul,
      OperatorPlus
    ) to 40.0, solve("10 + (7-2)*6"))
  }
}