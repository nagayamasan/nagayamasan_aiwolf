package jp.ynu.eis.forest.naga

import org.aiwolf.client.lib._
import org.aiwolf.common.data.Agent
import org.aiwolf.common.data.Player
import org.aiwolf.common.net.GameInfo
import org.aiwolf.common.net.GameSetting
import org.aiwolf.sample.lib.AbstractSeer
import org.aiwolf.sample.player.SampleSeer


class NagaSeer extends Nothing {
  private[java] var g = null

  def getName = "yama"

  def update(gameInfo: Nothing): Unit = {
    g = gameInfo
  }

  def initialize(gameInfo: Nothing, gameSetting: Nothing): Unit = {
  }

  def dayStart(): Unit = {
  }

  def talk: String = { //return new Content(new AgreeContentBuilder(TalkType.TALK, 1,5)).getText();
    g.getRole.toString
  }

  def whisper: String = null

  def vote: Nothing = null

  def attack: Nothing = null

  def divine: Nothing = null

  def guard: Nothing = null

  def finish(): Unit = {
  }
}
