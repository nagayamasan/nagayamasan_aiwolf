package jp.ynu.eis.forest.naga.result

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.NeoTalk
import org.aiwolf.common.data.{Agent, Talk}
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.util.Random

case class QuestionResult(analyzeList: mutable.MutableList[NeoTalk], questionClass: mutable.Map[String, Boolean]) {
  var questionTalk: mutable.MutableList[NeoTalk] = analyzeList
  def getAgentName: Option[Agent] ={
    if(questionTalk.nonEmpty) {
      var agentName = Option(Random.shuffle(questionTalk).head.getAgent)
      questionTalk.clear()

      return agentName

    }
    else{
      return Option.empty[Agent]
    }

  }

  def resetQuestionList: Unit ={
    return questionTalk.clear()
  }
}
