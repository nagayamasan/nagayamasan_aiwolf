package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.MeCab
import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

case class QuestionAnalyzer(ur: UtteranceResult){
  def getResult: QuestionResult ={
    val analist: mutable.MutableList[Talk] = ur.questionList
    val gameInfo = ur.dm.gameInfoList.last
    val responseAgent = gameInfo.getAgent
    val questionClass = mutable.Map("role" -> false, "vote" -> false, "reason" -> false, "who" -> false)
    ur.dm.taList.anaList.resc.wordData.foreach(f => {
      if(f.contains("役職")){
        questionClass("role") = true
      }
      if(f.contains("投票")){
        questionClass("vote") = true
      }
      if(f.contains("どうして") || f.contains("なぜ")){
        questionClass("reason") = true
      }
      if(f.contains("誰が")){
        questionClass("who") = true
      }
    })
    QuestionResult(analist, questionClass)
  }
}
