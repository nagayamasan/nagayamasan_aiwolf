package jp.ynu.eis.forest.naga.result


import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, NeoTalk}
import jp.ynu.eis.forest.naga.result.minds.SituationMind
import org.aiwolf.common.data.Talk

import scala.collection.mutable


case class UtteranceResult(dm: DialogManager, needQA: Boolean, mind: SituationMind) {
  val recentTalkList: mutable.MutableList[NeoTalk] = dm.neoTalkList.filter(_.getTurn == dm.neoTalkList.last.getTurn)

  //val reCentTalkList = dm.taList.talkList.takeRight(dm.gameInfoList.last.getAliveAgentList.size - 1)//自分いらない

  val question = dm.questionRegex
  val questionList = mutable.MutableList.empty[NeoTalk]
  recentTalkList.foreach{
    recenttalk =>
      if(question.findFirstIn(recenttalk.getText).isDefined &&
        recenttalk.getText.contains(">>" + dm.gameInfoList.last.getAgent)){
        recenttalk.isQuestion = true
        questionList += recenttalk
      }
  }
}
