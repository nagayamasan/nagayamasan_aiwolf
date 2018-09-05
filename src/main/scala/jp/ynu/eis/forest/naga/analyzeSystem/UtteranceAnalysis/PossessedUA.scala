package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.analyzeSystem._
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, NeoTalk}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds.{DivineResult, RoleCo, VoteCantChoose, VoteCo}
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Role, Species, Talk}

import scala.collection.mutable
import scala.util.Random

case class PossessedUA(dm: DialogManager) extends UtteranceAnalyzer {

  val divineWerwolfRatio = Random.nextInt(10) < 7
  seerDetective
  wolfDetective



  override def getResult: UtteranceResult = {
    analyze()
    super.getResult
  }
  override def analyze() : Unit = {
    if (gameInfo.getDay == 1 && dm.turn == 1) {
      mind = RoleCo(Role.SEER)
      //return "私は占い師です"
    }
    else if (gameInfo.getDay == 1 && dm.getTurn == 2) {
      val randomAgent: Agent = Random.shuffle(dm.agentList).last
      if (divineWerwolfRatio) {
        mind = DivineResult(randomAgent, Species.WEREWOLF)
      }
      else {
        mind = DivineResult(randomAgent, Species.HUMAN)
      }
      //return "誠に残念ながら" +  + "は" + Random.shuffle(judgeList).head + "でした。"
    } else if (gameInfo.getDay == 2 && dm.turn == 1) {
      mind = RoleCo(Role.POSSESSED)
      //return "あ、どーも僕が狂人です。"
    }else if(gameInfo.getDay == 1 && dm.getTurn == dm.VOTE_DECIDED_TURN){
      val vote: Agent = VoteDecider(dm).possessedVoteAction
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
  override def seerDetective:Unit  = {
    dm.seerList += dm.gameInfoList.last.getAgent
  }

  def wolfDetective = {
    val wolfWord = "人狼"
    Option(recentTalkList) match {
      case Some(list: mutable.MutableList[NeoTalk]) =>
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
