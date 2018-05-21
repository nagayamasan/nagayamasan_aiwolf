package jp.ynu.eis.forest.naga

import org.aiwolf.common.data.{Agent, Player, Role}
import org.aiwolf.common.net.GameInfo
import org.aiwolf.common.net.GameSetting
import org.aiwolf.common.net.TcpipClient
import java.util


object NagaPlayer {
  def main(args: Array[String]): Unit = {
    System.out.println()
    new Thread(new Server).start()
    val nl = new util.ArrayList[NagaPlayer]
    val cl = new util.ArrayList[TcpipClient]
    var i = 0
    while ( {
      i < 5
    }) {
      nl.add(new NagaPlayer)
      cl.add(new TcpipClient("localhost", 10000))
      cl.get(i).connect(nl.get(i))

      {
        i += 1; i - 1
      }
    }
  }
}
class NagaPlayer extends Player {
  private var player : NagaPerusona= _

  override def getName: String = {
    "naga"
  }
  override def update(gameInfo: GameInfo): Unit = player.update(gameInfo)

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

    player = gameInfo.getRole match {
      case Role.SEER => NagaSeer(gameInfo,gameSetting)
      case Role.POSSESSED =>  NagaPossessed(gameInfo,gameSetting)
      case Role.WEREWOLF => NagaWerewolf(gameInfo,gameSetting)
      case Role.VILLAGER => NagaVillager(gameInfo,gameSetting)
      case _ => NagaVillager(gameInfo,gameSetting)
    }
    player.initialize(gameInfo,gameSetting)
  }

  override def dayStart(): Unit = player.dayStart()

  override def talk(): String = player.talk()

  override def whisper(): String = player.whisper()

  override def vote(): Agent = player.vote()

  override def attack(): Agent = player.attack()

  override def divine(): Agent = player.divine()

  override def guard(): Agent = player.guard()

  override def finish(): Unit = player.finish()
}
