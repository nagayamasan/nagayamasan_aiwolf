package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, NeoTalk}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds.{PossessedDetect, VoteCantChoose, VoteCo}
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable


case class WolfUA(dm: DialogManager) extends UtteranceAnalyzer {
  seerDetective()
  possessedDetective()

  override def getResult: UtteranceResult = {
    analyze()
    super.getResult
  }

  override def analyze(): Unit = {
    if(gameInfo.getDay == 2 && dm.possList.nonEmpty && !dm.possdecFlag){
      dm.possdecFlag = true
      mind = PossessedDetect
      //return "狂人把握した"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == dm.VOTE_DECIDED_TURN){
      val vote: Agent = VoteDecider(dm).werewolfVoteAction
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
  override def seerDetective(): Unit = {
    super.seerDetective()

  }

  def possessedDetective(): Unit = {
    val possesedWord = "狂人"
    Option(recentTalkList) match {
      case Some(list: mutable.MutableList[NeoTalk]) =>
        recentTalkList.foreach(f => {
          if (f.getText.contains(possesedWord) && f.getDay == 2) {
            dm.possList += f.getAgent
          }
        })
      case None =>
        System.err.println("WolfUA.possessedDetective reCentTalklist was null")
    }

  }
}

