package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.client.lib.Content
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

case class SeerUA(dm : DialogManager) extends UtteranceAnalyzer {
  seerDetective
  override def getResult: UtteranceResult = {
    super.getResult
  }

  override def seerDetective: Agent = {
    dm.gameInfoList.last.getAgent
  }
}
