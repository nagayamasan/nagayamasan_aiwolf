package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

class DialogManager{
  val gameInfoList = mutable.MutableList.empty[GameInfo]
  val taList = new TalkAnalyzeList
  val seerList = mutable.MutableList.empty[Agent]

}

