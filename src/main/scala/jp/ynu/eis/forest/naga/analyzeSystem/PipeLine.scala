package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis._
import jp.ynu.eis.forest.naga.result._
import org.aiwolf.common.data.Role
import org.aiwolf.common.net.GameInfo

case class PipeLine(gameInfo: GameInfo, dm :DialogManager, ft : FullTalkList) {
  var uaResultOpt : Option[UtteranceResult] = Option.empty[UtteranceResult]
  dm.gameInfoList += gameInfo
  ft.collecting(gameInfo)
  uaResultOpt = Option(gameInfo.getRole match {
    case Role.SEER => SeerUA(ft,dm).getResult
    case Role.WEREWOLF => WolfUA(ft).getResult
    case Role.POSSESSED => PossessedUA(ft).getResult
    case Role.VILLAGER => VillagerUA(ft).getResult
    case _ => VillagerUA(ft).getResult
  })
  val uaResult: UtteranceResult = uaResultOpt.get
  var ugInput : String = ""

  if(uaResult.needQA){
    val qaResult :QuestionResult = QuestionAnalyzer(uaResult).getResult
    ugInput = AnswerGenerator(qaResult).getResresult
  }else{
    ugInput = uaResult.response
  }

  val ugResult :String = UtteranceGenerator(ugInput).getResult

  def getOutput:String={
    ugResult
  }

}