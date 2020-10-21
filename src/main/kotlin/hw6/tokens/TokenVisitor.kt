package hw6.tokens

interface TokenVisitor {
  fun visit(token: NumberToken)
  fun visit(token: Operator)
  fun visit(token: Brace)
}