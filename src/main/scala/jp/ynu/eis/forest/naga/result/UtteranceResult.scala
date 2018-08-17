package jp.ynu.eis.forest.naga.result

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.minds.SituationMind
import org.aiwolf.common.data.Talk

import scala.collection.mutable


case class UtteranceResult(dm: DialogManager, needQA: Boolean, mind: SituationMind) {
  val reCentTalkList = dm.taList.talkList.filter(_.getTurn == dm.taList.talkList.last.getTurn)

  //val reCentTalkList = dm.taList.talkList.takeRight(dm.gameInfoList.last.getAliveAgentList.size - 1)//自分いらない

  val question = dm.questionRegex
  val questionList = mutable.MutableList.empty[Talk]
  reCentTalkList.foreach{
     text =>
       if(question.findFirstIn(text.getText).isDefined && text.getText.contains(">>" + dm.gameInfoList.last.getAgent)){
         questionList += text

       }

   }

}
