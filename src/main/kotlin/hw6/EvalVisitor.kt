package hw6

import hw6.tokens.Brace
import hw6.tokens.NumberToken
import hw6.tokens.Operator
import hw6.tokens.TokenVisitor
import java.util.*

class EvalVisitor : TokenVisitor {
  private val stack = Stack<Double>()

  override fun visit(token: NumberToken) {
    stack.push(token.value)
  }

  override fun visit(token: Operator) {
    if (stack.size < 2) throw RuntimeException("Not enough numbers on stack")
    val b = stack.pop()
    val a = stack.pop()
    stack.push(token.eval(a, b))
  }

  override fun visit(token: Brace) {
  }

  fun getResult(): Double {
    if (stack.size != 1) throw RuntimeException("Stack size is not 1")
    return stack.peek()
  }
}