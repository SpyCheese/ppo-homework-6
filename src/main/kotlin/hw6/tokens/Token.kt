package hw6.tokens

interface Token {
  fun accept(visitor: TokenVisitor)
}