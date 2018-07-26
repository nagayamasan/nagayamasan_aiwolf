package jp.ynu.eis.forest.naga.analyzeSystem.dialog.AnalyzeList

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective.{KeldicSource, McreSource, WordWolfSource}
import org.aiwolf.common.data.Agent

import scala.collection.mutable

class ResultOfOppDet {
  var kanoList = mutable.MutableList.empty[Agent]
  var keldicList = mutable.MutableList.empty[Agent]
  var mcreList = mutable.MutableList.empty[Agent]
  var wordWolfList = mutable.MutableList.empty[Agent]
  var indigoList = mutable.MutableList.empty[Agent]

  var kano = Option.empty[Agent]
  var keldic = Option.empty[Agent]
  var mcre = Option.empty[Agent]
  var wordWolf = Option.empty[Agent]
  var indigo = Option.empty[Agent]
}
