package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds.{RoleCo, VoteCantChoose, VoteCo}
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Role, Talk}

import scala.collection.mutable

case class VillagerUA(dm: DialogManager) extends UtteranceAnalyzer {
  seerDetective
  wolfDetective
  //opponentDetective
  override def getResult: UtteranceResult ={
    super.getResult
  }

  override def analyze(): Unit = {
    if(gameInfo.getDay == 2 && dm.turn == 1){
      mind = RoleCo(Role.WEREWOLF)
      //return "あ、どーも私が人狼です。"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == dm.VOTE_DECIDED_TURN){
      val vote: Agent = VoteDecider(dm).villagerVoteAction
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
  def wolfDetective = {
    val wolfWord = "人狼"
    Option(recentTalkList) match {
      case Some(list: mutable.MutableList[Talk]) =>
        recentTalkList.foreach(f => {
          if (f.getText.contains(wolfWord) && f.getDay == 2) {
            dm.wolfList += f.getAgent
          }
        })
      case None =>
        System.err.println("WolfUA.wolfDetective reCentTalklist was null")
    }

  }
}
