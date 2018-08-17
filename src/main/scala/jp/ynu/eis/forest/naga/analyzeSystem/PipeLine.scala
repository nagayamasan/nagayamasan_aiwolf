package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis._
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result._
import jp.ynu.eis.forest.naga.result.minds.{RandomTalk, SituationMind}
import org.aiwolf.common.data.Role
import org.aiwolf.common.net.GameInfo

case class PipeLine(dm :DialogManager) {
  var uaResultOpt : Option[UtteranceResult] = Option.empty[UtteranceResult]
  dm.addTurn

  uaResultOpt = Option(dm.gameInfoList.last.getRole match {
    case Role.SEER => SeerUA(dm).getResult
    case Role.WEREWOLF => WolfUA(dm).getResult
    case Role.POSSESSED => PossessedUA(dm).getResult
    case Role.VILLAGER => VillagerUA(dm).getResult
    case _ => VillagerUA(dm).getResult
  })
  val uaResult: UtteranceResult = uaResultOpt.get
  var ugInput : SituationMind = RandomTalk//default

  if(uaResult.needQA){
    val qaResult :QuestionResult = QuestionAnalyzer(uaResult).getResult
    ugInput = AnswerGenerator(qaResult,dm).getResresult

  }else{
    ugInput = uaResult.mind
  }

  val ugResult :String = UtteranceGenerator(ugInput, dm).getResult

  def getOutput:String={
    ugResult
  }

}