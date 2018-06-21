package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.{DialogManager, FullTalkList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

case class SeerUA(MassTalk : FullTalkList, dm : DialogManager) extends UtteranceAnalyzer {
  seerDetective
  override def getResult: UtteranceResult = {
    super.getResult
  }

  override def seerDetective: Agent = {
    dm.gameInfoList.last.getAgent
  }
}
