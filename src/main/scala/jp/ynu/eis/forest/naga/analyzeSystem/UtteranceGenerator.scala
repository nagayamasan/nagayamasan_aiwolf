package jp.ynu.eis.forest.naga.analyzeSystem

import org.aiwolf.common.net.GameInfo

import scala.io.Source
import scala.util.Random

case class UtteranceGenerator(answerResult: String, gameInfo: GameInfo) {

  def getResult: String= {
    val resource = "resource/fact"
    if (answerResult.nonEmpty) {
      return answerResult
    }
    else if (gameInfo.getAgent.toString == "Agent[01]") {
      val resource = "resource/fact"
      val file = Source.fromFile(s"${resource}/kanofact.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))
    }
    /*else if(gameInfo.getAgent.toString == "Agent[02]"){
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
    */else{
      val file = Source.fromFile(s"${resource}/naga.txt")
      val sentenceList = file.getLines.toList
      sentenceList(Random.nextInt(sentenceList.size))


    }
  }
}
