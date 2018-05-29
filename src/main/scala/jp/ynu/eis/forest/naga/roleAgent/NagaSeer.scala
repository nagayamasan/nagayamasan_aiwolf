package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.{DialogManager, PipeLine}
import org.aiwolf.common.data.{Agent, Judge, Role, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable
import scala.math.random

case class NagaSeer(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {

  val dm = new DialogManager
  var turn = 0

  var divineList = mutable.MutableList.empty[Judge]


  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
    dm.gameInfoList += gameInfo
    if (gameInfo.getDivineResult != null) {
      divineList += gameInfo.getDivineResult
    }
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {
    dm.gameInfoList = mutable.MutableList.empty[GameInfo]
    divineList = mutable.MutableList.empty[Judge]
  }

  override def dayStart(): Unit = {
    turn = 0
  }

  override def talk(): String = {
    new PipeLine(gameInfo, dm).getOutput
  }

  override def vote(): Agent = {
    val divinedWerewolf = divineList.filter(_.getResult == Species.WEREWOLF)
    if (divinedWerewolf.nonEmpty) {
      return divinedWerewolf.head.getTarget
    }
    val candidateAgentList = gameInfo.getAliveAgentList
    val divinedHumanList = divineList.filter(_.getResult == Species.HUMAN)
    divinedHumanList.foreach(f => {
      candidateAgentList.remove(f.getTarget)
    })

    candidateAgentList.get(candidateAgentList.size() * random().toInt)
  }

  def divine(): Agent = {
    val candidateAgentList = gameInfo.getAliveAgentList
    if (divineList.nonEmpty) {
      divineList.foreach(f => {
        candidateAgentList.remove(f.getTarget)
      })
    }

    candidateAgentList.get(candidateAgentList.size() * random().toInt)
  }

  override def finish(): Unit = {

  }
}
