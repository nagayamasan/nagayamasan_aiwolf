package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.{DialogManager, PipeLine}
import org.aiwolf.common.data.{Agent, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable
import scala.math.random

case class NagaVillager(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {

  val dm = new DialogManager

  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
    dm.gameInfoList += gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {
    dm.gameInfoList = mutable.MutableList.empty[GameInfo]
  }

  override def dayStart(): Unit = {

  }

  override def talk(): String = {
    new PipeLine(gameInfo, dm).getOutput
  }

  override def vote(): Agent = {
    val candidateAgentList = gameInfo.getAliveAgentList
    candidateAgentList.get(candidateAgentList.size() * random().toInt)
  }

  override def finish(): Unit = {

  }
}
