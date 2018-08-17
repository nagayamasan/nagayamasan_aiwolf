package jp.ynu.eis.forest.naga.roleAgent

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Judge, Role, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable
import scala.util.Random

case class NagaSeer(var gameInfo: GameInfo, var gameSetting: GameSetting) extends NagaPersona {
  //val divineList: mutable.MutableList[Judge] = mutable.MutableList.empty[Judge]


  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
    if (gameInfo.getDivineResult != null) {
      dm.divineList += gameInfo.getDivineResult
    }
  }



  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {
    /*val spMap = Map("HUMAN" -> "人間", "WEREWOLF" -> "人狼")
    if(gameInfo.getDay == 1 && dm.turn == 0){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      dm.addTurn
      return "私は占い師です"

    }else if(gameInfo.getDay == 1 && dm.getTurn == 1){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      dm.addTurn
      return "占いの結果" + gameInfo.getDivineResult.getTarget + "は" + spMap(gameInfo.getDivineResult.getResult.toString) + "でした。"
    }else if(gameInfo.getDay == 2 && dm.getTurn == 1){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      dm.addTurn
      return "占いの結果" + gameInfo.getDivineResult.getTarget + "は" + spMap(gameInfo.getDivineResult.getResult.toString) + "でした。"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == VoteDecideTurn){
      dm.addTurn
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      if(vote != null && vote != gameInfo.getAgent){
        return vote.toString + "に投票するわ。"
      }
      else{
        return "投票先絞れない。"
      }
    }*/
    super.talk()

  }

  override def vote: Agent = {
    /*val divinedWerewolfList = divineList.filter(_.getResult == Species.WEREWOLF)
    if (divinedWerewolfList.nonEmpty) {
      return divinedWerewolfList.head.getTarget
    }
    val candidateAgentList = gameInfo.getAliveAgentList
    val divinedHumanList = divineList.filter(_.getResult == Species.HUMAN)
    divinedHumanList.foreach(f => {
      candidateAgentList.remove(f.getTarget)
    })

    candidateAgentList.get(Random.nextInt(candidateAgentList.size()))*/

    super.vote()
  }

  def divine(): Agent = {
    val candidateAgentList = dm.agentListChange(gameInfo.getAliveAgentList).filter(_ != gameInfo.getAgent)
    //println(candidateAgentList)

    if(candidateAgentList.nonEmpty){
      Random.shuffle(candidateAgentList).head
    }else{
      println("iru")
      null
      //Random.shuffle(candidateAgentList).head
    }

  }

  override def finish(): Unit = {

  }
}
