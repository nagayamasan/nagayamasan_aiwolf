package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.net.GameInfo

import scala.io.Source
import scala.util.Random

case class UtteranceGenerator(answerResult: String, dm: DialogManager) {

  def getResult: String= {
    val coin = List(true,false)
    val resource = "resource"
    val agentList = dm.agentListChange(dm.gameInfoList.last.getAliveAgentList).filter{
      f => f != dm.gameInfoList.last.getAgent
    }

    if (answerResult.nonEmpty) {
      return answerResult
    }
    /*else if (dm.gameInfoList.last.getAgent.toString == "Agent[01]") {
      val file = Source.fromFile(s"${resource}/fact/kanofact.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))

    }
    else if(gameInfo.getAgent.toString == "Agent[02]"){
      val file = Source.fromFile(s"${resource}/mcrefact.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))
    }else if(gameInfo.getAgent.toString == "Agent[03]"){
      val file = Source.fromFile(s"${resource}/keldicfact.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))
    }else if(gameInfo.getAgent.toString == "Agent[04]"){
      val file = Source.fromFile(s"${resource}/wordwolffact.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))
    }
    */
    else if(Random.shuffle(coin).head){
      val file = Source.fromFile(s"${resource}/naga.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))

    }
    else{
      val file = Source.fromFile(s"${resource}/question.txt")
      val sentenceList = file.getLines.toList
      val enemyName  = Random.shuffle(agentList).head
      ">>"+ enemyName + " " + enemyName + Random.shuffle(sentenceList).head
    }
  }
}
