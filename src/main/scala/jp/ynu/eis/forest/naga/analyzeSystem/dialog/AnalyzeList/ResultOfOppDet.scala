package jp.ynu.eis.forest.naga.analyzeSystem.dialog.AnalyzeList

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective.{KeldicSource, McreSource, WordWolfSource}
import org.aiwolf.common.data.Agent

import scala.collection.mutable

class ResultOfOppDet {
  val kanoList = mutable.MutableList.empty[Agent]
  val keldicList = mutable.MutableList.empty[Agent]
  val mcreList = mutable.MutableList.empty[Agent]
  val wordwolfList = mutable.MutableList.empty[Agent]
  val indigoList = mutable.MutableList.empty[Agent]

  var kano = Option.empty[Agent]
  var keldic : Option[Agent] = Option.empty[Agent]
  var mcre = Option.empty[Agent]
  var wordWolf = Option.empty[Agent]
  var indigo = Option.empty[Agent]
}
