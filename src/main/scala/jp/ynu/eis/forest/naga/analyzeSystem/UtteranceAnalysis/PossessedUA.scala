package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem._
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Agent, Talk}

case class PossessedUA(MassTalk : FullTalkList) extends UtteranceAnalyzer {
  seerDetective
  override def getResult: UtteranceResult = {
    super.getResult
  }
}
