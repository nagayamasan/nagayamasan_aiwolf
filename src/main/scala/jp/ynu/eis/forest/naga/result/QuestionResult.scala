package jp.ynu.eis.forest.naga.result

import org.aiwolf.common.data.{Agent, Talk}
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.util.Random

case class QuestionResult(analist: mutable.MutableList[Talk], questionClass: mutable.Map[String, Boolean]) {
  var questionTalk: mutable.MutableList[Talk] = analist

  def getAgentName: String ={
    if(questionTalk.nonEmpty) {
      var agentName = ">>" + Random.shuffle(questionTalk).head.getAgent + " "
      questionTalk.clear()

      agentName

    }
    else{""}

  }
  def resetQuestionList: Unit ={
    questionTalk.clear()

  }
}
