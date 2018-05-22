package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result.UttranceResult
import org.aiwolf.common.net.GameInfo

case class UttranceAnalyzer(gameInfo: GameInfo) {

  def getResult: UttranceResult = {
    UttranceResult("",true,"")
  }
}
