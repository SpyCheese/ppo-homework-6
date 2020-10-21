package hw6.tokens

abstract class Operator : Token {
  abstract val char: Char
  abstract val precedence: Int

  abstract fun eval(a: Double, b: Double): Double

  override fun accept(visitor: TokenVisitor) {
    visitor.visit(this)
  }
}

object OperatorPlus : Operator() {
  override val char = '+'
  override val precedence = 0
  override fun eval(a: Double, b: Double) = a + b
}

object OperatorMinus : Operator() {
  override val char = '-'
  override val precedence = 0
  override fun eval(a: Double, b: Double) = a - b
}

object OperatorMul : Operator() {
  override val char = '*'
  override val precedence = 1
  override fun eval(a: Double, b: Double) = a * b
}

object OperatorDiv : Operator() {
  override val char = '/'
  override val precedence = 1
  override fun eval(a: Double, b: Double) = a / b
}