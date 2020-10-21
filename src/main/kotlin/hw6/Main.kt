package hw6

import hw6.tokens.Token
import kotlin.system.exitProcess

fun solve(s: String): Pair<List<Token>, Double> {
  val parser = ParserVisitor()
  Tokenizer.tokenize(s, parser)
  val tokens = parser.getResult()
  val evaluator = EvalVisitor()
  tokens.forEach { it.accept(evaluator) }
  return tokens to evaluator.getResult()
}

fun main() {
  val s = readLine()!!
  val (tokens, result) = try {
    solve(s)
  } catch (e: RuntimeException) {
    println(e.message)
    exitProcess(1)
  }
  tokens.forEach { it.accept(PrintVisitor) }
  println()
  println(result)
}