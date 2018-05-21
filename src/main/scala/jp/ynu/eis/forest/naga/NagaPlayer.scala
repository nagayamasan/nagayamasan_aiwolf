package jp.ynu.eis.forest.naga

import org.aiwolf.common.data.Agent
import org.aiwolf.common.data.Player
import org.aiwolf.common.data.Role
import org.aiwolf.common.net.GameInfo
import org.aiwolf.common.net.GameSetting
import org.aiwolf.common.net.TcpipClient
import java.util

import javax.management.relation.Role


object NagaPlayer {
  def main(args: Array[String]): Unit = {
    System.out.println()
    new Thread(new Server).start()
    val nl = new util.ArrayList[NagaPlayer]
    val cl = new util.ArrayList[Nothing]
    var i = 0
    while ( {
      i < 5
    }) {
      nl.add(new NagaPlayer)
      cl.add(new Nothing("localhost", 10000))
      cl.get(i).connect(nl.get(i))

      {
        i += 1; i - 1
      }
    }
  }
}

class NagaPlayer extends Nothing {
  private var player = null

  def getName = "yama"

  def update(gameInfo: Nothing): Unit = {
    player.update(gameInfo)
  }

  def initialize(gameInfo: Nothing, gameSetting: Nothing): Unit = {
    player = new NagaSeer
    if (gameInfo.getRole eq Role.SEER) player = new NagaSeer
  }

  def dayStart(): Unit = {
  }

  def talk: String = player.talk

  def whisper: String = null

  def vote: Nothing = null

  def attack: Nothing = null

  def divine: Nothing = null

  def guard: Nothing = null

  def finish(): Unit = {
  }
}
