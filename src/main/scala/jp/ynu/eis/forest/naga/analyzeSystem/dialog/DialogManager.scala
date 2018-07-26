package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

class DialogManager{

  val gameInfoList = mutable.MutableList.empty[GameInfo]
  val taList = new TalkAnalyzeList
  val seerList = mutable.MutableList.empty[Agent]
  val wolfList = mutable.MutableList.empty[Agent]
  val possList = mutable.MutableList.empty[Agent]
  var turn = 0

  def resetTurn: Unit = {
    turn = 0
  }

  def addTurn: Unit = {
    turn += 1
  }

  def getTurn: Int = {
    turn
  }

}

