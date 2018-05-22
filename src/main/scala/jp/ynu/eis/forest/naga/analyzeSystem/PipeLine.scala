package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.net.GameInfo

class PipeLine(gameInfo: GameInfo, dm :DialogManager) {

  dm.gameInfoList += gameInfo

  val uaResult : UttranceResult= UttranceAnalyzer(gameInfo).getResult
  var ugInput : String = ""
  if(uaResult.needQA){
    val qaResult :QuestionResult = QuestionAnalyzer(uaResult).getResult
    ugInput = AnswerGenerater(qaResult).getResresult
  }else{
    ugInput = uaResult.response
  }

  val ugResult :String = UttaranceGenerater(ugInput).getResult

  def getOutput:String={
    ugResult
  }

}