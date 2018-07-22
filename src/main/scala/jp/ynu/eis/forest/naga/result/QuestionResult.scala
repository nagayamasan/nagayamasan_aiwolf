package jp.ynu.eis.forest.naga.result

import scala.collection.mutable

case class QuestionResult(spell: String, questionClass: mutable.Map[String, Boolean]) {
}
