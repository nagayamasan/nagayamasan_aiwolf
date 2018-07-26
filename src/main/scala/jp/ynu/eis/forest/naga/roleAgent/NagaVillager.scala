package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.{Agent, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}
import scala.collection.JavaConversions._

import scala.collection.mutable
import scala.math.random

case class NagaVillager(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {

  override def update(gameInfo: GameInfo): Unit = {
    dm.gameInfoList += gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {
    //dm.gameInfoList = mutable.MutableList.empty[GameInfo]
  }

  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {
    super.talk()
  }

  override def vote(): Agent = {
    val candidateAgentList = gameInfo.getAliveAgentList
    candidateAgentList.removeAll(dm.seerList)
    candidateAgentList.get(candidateAgentList.size() * random().toInt)
  }

  override def finish(): Unit = {

  }
}
