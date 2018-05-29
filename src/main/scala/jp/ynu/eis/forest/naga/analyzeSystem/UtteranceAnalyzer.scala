package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.net.GameInfo

case class UtteranceAnalyzer(gameInfo: GameInfo) {

  def getResult: UtteranceResult = {
    UtteranceResult("",true,"")
  }
}