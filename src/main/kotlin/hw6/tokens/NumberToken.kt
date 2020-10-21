package hw6.tokens

data class NumberToken(val value: Double) : Token {
  override fun accept(visitor: TokenVisitor) {
    visitor.visit(this)
  }
}