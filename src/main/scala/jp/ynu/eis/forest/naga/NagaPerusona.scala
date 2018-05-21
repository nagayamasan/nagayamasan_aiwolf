package jp.ynu.eis.forest.naga

import org.aiwolf.common.data.{Agent, Player}
import org.aiwolf.common.net.{GameInfo, GameSetting}

trait NagaPerusona {
  def getName: String = "naga"

  def update(gameInfo: GameInfo): Unit

  def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit

  def dayStart(): Unit

  def talk(): String

  def whisper(): String

  def vote(): Agent

  def attack(): Agent

  def divine(): Agent

  def guard(): Agent

  def finish(): Unit
}
