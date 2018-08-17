package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.QuestionResult
import jp.ynu.eis.forest.naga.result.minds._
import org.aiwolf.common.data.Agent

import scala.util.Random

case class AnswerGenerator(question: QuestionResult,dm : DialogManager){
  def getResresult: SituationMind = {
    var resresult = ""

    val ankerTarget: Agent = question.getAgentName.getOrElse(Random.shuffle(dm.agentList).head)

    if(question.questionClass("who")){
      resresult = "お前だよ"
    }else if(question.questionClass("vote")){
      resresult = "教えない。"
    }else if(question.questionClass("role")){
      resresult = "ひみつ。"
    }else if(question.questionClass("reason")){
      resresult = "なんとなくだよ。"
    }

    if(!resresult.isEmpty){

      Answer(ankerTarget,resresult)

    }
    else{
      RandomTalk

    }
  }
}
