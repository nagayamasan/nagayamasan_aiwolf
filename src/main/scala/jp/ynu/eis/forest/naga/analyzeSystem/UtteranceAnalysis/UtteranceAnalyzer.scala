package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.{DialogManager, FullTalkList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

trait UtteranceAnalyzer {
  val MassTalk : FullTalkList
  val tl: mutable.MutableList[Talk] = MassTalk.TalkList
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
