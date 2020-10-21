package hw6

import hw6.tokens.*
import java.util.*

class ParserVisitor : TokenVisitor {
  private val stack = Stack<Operator?>()
  private val result = mutableListOf<Token>()

  override fun visit(token: NumberToken) {
    result.add(token)
  }

  override fun visit(token: Operator) {
    while (!stack.empty()) {
      val top = stack.peek()
      if (top == null || top.precedence < token.precedence) break
      result.add(top)
      stack.pop()
    }
    stack.push(token)
  }

  override fun visit(token: Brace) {
    if (token.opening) {
      stack.push(null)
    } else {
      while (true) {
        if (stack.empty()) throw RuntimeException("Invalid brace sequence")
        val top = stack.pop() ?: break
        result.add(top)
      }
    }
  }

  fun getResult(): List<Token> {
    while (!stack.empty()) {
      val top = stack.pop() ?: throw RuntimeException("Invalid brace sequence")
      result.add(top)
    }
    return result
  }
}