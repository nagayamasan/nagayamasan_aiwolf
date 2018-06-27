package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

case class NagaWerewolf(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {

  val dm = new DialogManager

  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {

  }

  override def talk(): String = {
    new PipeLine(gameInfo, dm).getOutput
  }

  def whisper(): String = {
    "kusa"
  }

  override def vote(): Agent = {
    null
  }

  def attack(): Agent = {
    null
  }
  override def finish(): Unit = {

  }
}
