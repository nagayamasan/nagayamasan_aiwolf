package jp.ynu.eis.forest.naga

import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

case class NagaWerewolf(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPerusona {

  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {

  }

  override def talk(): String = {
    "mamoru"
  }

  override def whisper(): String = {
    "kusa"
  }

  override def vote(): Agent = {
    null
  }

  override def attack(): Agent = {
    null
  }

  override def divine(): Agent = {
    null
  }

  override def guard(): Agent = {
    null
  }

  override def finish(): Unit = {

  }
}
