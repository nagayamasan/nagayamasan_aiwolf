package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager

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
      //Random.nextBooleanって知ってる？
    else if(Random.shuffle(coin).head){

      val sentenceList = OpponentDetective.nagaSentenceList
      sentenceList(Random.nextInt(sentenceList.size))

    }
    else{

      val sentenceList = OpponentDetective.questionSentenceList
      val enemyName  = Random.shuffle(agentList).head
      ">>"+ enemyName + " " + enemyName + Random.shuffle(sentenceList).head
    }
  }
}
