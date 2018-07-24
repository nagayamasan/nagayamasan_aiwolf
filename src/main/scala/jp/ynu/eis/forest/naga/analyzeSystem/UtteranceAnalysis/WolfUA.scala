package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable


case class WolfUA(dm: DialogManager) extends UtteranceAnalyzer {
  seerDetective
  possessedDetective

  override def getResult: UtteranceResult = {
    super.getResult
  }

  override def seerDetective: Unit = {
    super.seerDetective

  }

  def possessedDetective = {
    val possesedWord = "狂人"
    Option(reCentTalklist) match {
      case Some(list: mutable.MutableList[Talk]) =>
        reCentTalklist.foreach(f => {
          if (f.getText.contains(possesedWord)) {
            dm.possList += f.getAgent
          }
        })
      case None =>
        System.err.println("WolfUA.possessedDetective reCentTalklist was null")
    }

  }
}

