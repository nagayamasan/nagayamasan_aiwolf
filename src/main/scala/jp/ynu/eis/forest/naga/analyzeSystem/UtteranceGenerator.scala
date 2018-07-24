package jp.ynu.eis.forest.naga.analyzeSystem

import scala.io.Source
import scala.util.Random

case class UtteranceGenerator(answerResult: String) {

  def getResult: String={
    if(answerResult.nonEmpty){
      return answerResult
    }
    val resource = "resource/fact"
    val kanoFile = Source.fromFile(s"${resource}/kanofact.txt")
    val mcreFile = Source.fromFile(s"${resource}/mcrefact.txt")
    val keldicFile = Source.fromFile(s"${resource}/keldicfact.txt")
    val wwFile = Source.fromFile(s"${resource}/wordwolffact.txt")
    val kanoList = kanoFile.getLines.toList
    val mcreList = mcreFile.getLines.toList
    val keldicList = keldicFile.getLines.toList
    val wwList = wwFile.getLines.toList
    var list = kanoList ::: mcreList ::: keldicList ::: wwList
    list(Random.nextInt(list.size))
  }

}
