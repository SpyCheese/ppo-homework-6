package hw6

import hw6.tokens.Brace
import hw6.tokens.NumberToken
import hw6.tokens.Operator
import hw6.tokens.TokenVisitor

object PrintVisitor : TokenVisitor {
  override fun visit(token: NumberToken) {
    print("${token.value} ")
  }

  override fun visit(token: Operator) {
    print("${token.char} ")
  }

  override fun visit(token: Brace) {
    print("${if (token.opening) '(' else ')'} ")
  }
}