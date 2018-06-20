package jp.ynu.eis.forest.naga.analyzeSystem

import java.util

import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

case class UtteranceAnalyzer(gameInfo: GameInfo) {
  def seerCo: Boolean = {
    val seercon: util.List[Talk] = gameInfo.getTalkList
    true
  }
  def getResult: UtteranceResult = {
    val tl: util.List[Talk] =  gameInfo.getTalkList
    val res: String = "そだね"
    UtteranceResult(tl ,true,"")

  }
}
