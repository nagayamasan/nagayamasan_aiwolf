package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result._

case class QuestionAnalyzer(ur: UtteranceResult){
  def getResult: QuestionResult ={
    /*
    val str = "大きなのっぽの古時計を買った。"

    val s: String = s"echo $str"#| "cabocha -f1" !!
    println(s)
    */

    QuestionResult(spell = "uaaa")
  }
}
