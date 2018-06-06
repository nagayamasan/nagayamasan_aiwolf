package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.net.GameInfo

class PipeLine(gameInfo: GameInfo, dm :DialogManager) {

  dm.gameInfoList += gameInfo

  val uaResult : UtteranceResult= UtteranceAnalyzer(gameInfo).getResult
  var ugInput : String = ""
  if(uaResult.needQA){
    val qaResult :QuestionResult = QuestionAnalyzer(uaResult).getResult
    ugInput = AnswerGenerator(qaResult).getResresult
  }else{
    ugInput = uaResult.response
  }

  val ugResult :String = UtteranceGenerater(ugInput).getResult

  def getOutput:String={
    ugResult
  }

}