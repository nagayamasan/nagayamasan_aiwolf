package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result._

case class QuestionAnalyzer(ur: UttranceResult){
  def getResult: QuestionResult ={
    QuestionResult(spell = "uaaa")
  }
}
