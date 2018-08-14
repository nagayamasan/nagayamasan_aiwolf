package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem._
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Agent, Talk}

import scala.collection.mutable

case class PossessedUA(dm: DialogManager) extends UtteranceAnalyzer {
  seerDetective
  wolfDetective

  override def getResult: UtteranceResult = {
    super.getResult
  }

  override def seerDetective  = {
    dm.seerList += dm.gameInfoList.last.getAgent
  }
  def wolfDetective = {
    val wolfWord = "人狼"
    Option(reCentTalklist) match {
      case Some(list: mutable.MutableList[Talk]) =>
        reCentTalklist.foreach(f => {
          if (f.getText.contains(wolfWord) && f.getDay == 2) {
            dm.wolfList += f.getAgent
          }
        })
      case None =>
        System.err.println("WolfUA.wolfDetective reCentTalklist was null")
    }

  }
}
