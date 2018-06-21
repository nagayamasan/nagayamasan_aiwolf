package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem.{DialogManager, FullTalkList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.net.GameInfo


case class WolfUA(MassTalk : FullTalkList) extends UtteranceAnalyzer {
  seerDetective
  override def getResult: UtteranceResult = {
    super.getResult
  }
}

