package jp.ynu.eis.forest.naga

import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.GameInfo
import org.aiwolf.common.net.GameSetting



case class NagaSeer(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPerusona{
  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {

  }

  override def talk(): String = {
    "kusahaeru"
  }

  override def whisper(): String = {
    "I am whisper"
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
