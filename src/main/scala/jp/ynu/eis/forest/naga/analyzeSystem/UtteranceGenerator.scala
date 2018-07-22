package jp.ynu.eis.forest.naga.analyzeSystem

import scala.io.Source
import scala.util.Random

case class UtteranceGenerator(answerResult: String) {

  def getResult: String={
    if(answerResult.nonEmpty){
      return answerResult
    }
    val resource = "resource"
    val file = Source.fromFile(s"${resource}/kanofact.txt")
    val kanoList = file.getLines.toList
    kanoList(Random.nextInt(kanoList.size))
  }

}
