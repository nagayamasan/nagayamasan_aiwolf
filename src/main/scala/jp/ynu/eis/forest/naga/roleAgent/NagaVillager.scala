package jp.ynu.eis.forest.naga.roleAgent

import scala.util.Random
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.{Agent, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.math.random

case class NagaVillager(var gameInfo: GameInfo, var gameSetting: GameSetting) extends NagaPersona {

  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {
    //dm.gameInfoList = mutable.MutableList.empty[GameInfo]
  }

  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {
    if(gameInfo.getDay == 2 && dm.turn == 1){
      dm.addTurn
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      return "あ、どーも私が人狼です。"
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
    }
    super.talk()

  }
  override def vote(): Agent = {
    val candidateAgentList = dm.agentListChange(gameInfo.getAliveAgentList).filter(_ != gameInfo.getAgent)
    dm.seerList.foreach{
      f =>
        candidateAgentList.filter(ag => ag== f)
    }
    if(candidateAgentList.nonEmpty) {
      dm.seerList.foreach{
        f =>
          candidateAgentList.filter(ag => ag== f)

      }
      Random.shuffle(candidateAgentList).head

    }

    else{
      null

    }
  }

  override def finish(): Unit = {

  }
}
