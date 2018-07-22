package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.MeCab
import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.net.GameInfo

case class QuestionAnalyzer(ur: UtteranceResult){
  def getResult: QuestionResult ={
    val analist = ur.questionList
    val gameInfo = ur.dm.gameInfoList
    if(ur.dm.taList.anaList.resc.wordData.head.contains(gameInfo.last.getAgent)){

    }

    analist
    QuestionResult(spell = "uaaa")
  }
}
