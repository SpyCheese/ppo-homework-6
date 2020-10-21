package hw6.tokens

data class Brace(val opening: Boolean) : Token {
  override fun accept(visitor: TokenVisitor) {
    visitor.visit(this)
  }
}