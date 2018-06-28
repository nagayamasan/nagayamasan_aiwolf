package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

trait UtteranceAnalyzer {
  val dm : DialogManager
  val tl: mutable.MutableList[Talk] = dm.taList.talkList
  def getResult: UtteranceResult = {
    UtteranceResult(tl, true, "")
  }

  def seerDetective: Agent = {
    val SeerLikeSpell = tl.last.getText

    //なんかの解析

    tl.last.getAgent
    //会話が進む毎に占い師の判定をする
  }
}
