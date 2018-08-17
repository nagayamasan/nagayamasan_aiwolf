package jp.ynu.eis.forest.naga.result

import org.aiwolf.common.data.{Agent, Talk}
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.util.Random

case class QuestionResult(analist: mutable.MutableList[Talk], questionClass: mutable.Map[String, Boolean]) {
  var questionTalk: mutable.MutableList[Talk] = analist

  def getAgentName: Option[Agent] ={
    if(questionTalk.nonEmpty) {
      var agentName = Option(Random.shuffle(questionTalk).head.getAgent)
      questionTalk.clear()

      agentName

    }
    else{
      Option.empty[Agent]
    }

  }
  def resetQuestionList: Unit ={
    questionTalk.clear()

  }
}
