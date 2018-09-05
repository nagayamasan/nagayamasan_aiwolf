package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds._
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.client.lib.Content
import org.aiwolf.common.data.{Agent, Role, Talk}
import org.aiwolf.common.net.GameInfo

case class SeerUA(dm : DialogManager) extends UtteranceAnalyzer {
  //seerDetective


  override def getResult: UtteranceResult = {
    analyze()
    super.getResult
  }

  override def analyze() : Unit ={
    //val spMap = Map("HUMAN" -> "人間", "WEREWOLF" -> "人狼")
    if(gameInfo.getDay == 1 && dm.turn == 1){
      mind = RoleCo(Role.SEER)
      //return "私は占い師です"

    }else if(gameInfo.getDay == 1 && dm.getTurn == 2){
      mind = DivineResult(gameInfo.getDivineResult.getTarget, gameInfo.getDivineResult.getResult)
      //return "占いの結果" + gameInfo.getDivineResult.getTarget + "は" + spMap(gameInfo.getDivineResult.getResult.toString) + "でした。"
    }else if(gameInfo.getDay == 2 && dm.getTurn == 2){
      mind = DivineResult(gameInfo.getDivineResult.getTarget, gameInfo.getDivineResult.getResult)
      //return "占いの結果" + gameInfo.getDivineResult.getTarget + "は" + spMap(gameInfo.getDivineResult.getResult.toString) + "でした。"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == dm.VOTE_DECIDED_TURN){
      val vote: Agent = VoteDecider(dm).seerVoteAction
      if(vote != null && vote != gameInfo.getAgent){
        mind = VoteCo(vote)
        //return vote.toString + "に投票するわ。"
      }
      else{
        mind = VoteCantChoose
        //return "投票先絞れない。"
      }
    }
  }
}
