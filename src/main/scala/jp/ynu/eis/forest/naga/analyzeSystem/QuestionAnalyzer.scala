package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.MeCab
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.NeoTalk
import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

case class QuestionAnalyzer(ur: UtteranceResult){

  def getResult: QuestionResult ={
    val questionList: mutable.MutableList[NeoTalk] = ur.questionList
    val gameInfo = ur.dm.gameInfoList.last
    val recentTalkList: mutable.MutableList[NeoTalk] = ur.dm.neoTalkList.filter(_.getTurn == ur.dm.neoTalkList.last.getTurn).filter(_.getAgent != gameInfo.getAgent)
    val responseAgent = gameInfo.getAgent
    val questionClass = mutable.Map("role" -> false, "vote" -> false, "reason" -> false, "who" -> false)
    recentTalkList.foreach{
      recentTalk =>
        if(recentTalk.getMeCabData.wordData.contains("役職")){
          questionClass("role") = true
        }
        if(recentTalk.getMeCabData.wordData.contains("投票")){
          questionClass("vote") = true
        }
        if(recentTalk.getMeCabData.wordData.contains("どうして") || recentTalk.getMeCabData.wordData.contains("なぜ")){
          questionClass("reason") = true
        }
        if(recentTalk.getMeCabData.wordData.contains("誰が")){
          questionClass("who") = true
        }
    }
    QuestionResult(questionList, questionClass)
  }
}
