package jp.ynu.eis.forest.naga.roleAgent

import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

trait NagaPersona {
  val name : String = "naga"


  def update(gameInfo: GameInfo): Unit

  def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit

  def dayStart(): Unit

  def talk(): String

  def vote(): Agent

  def finish(): Unit
}
