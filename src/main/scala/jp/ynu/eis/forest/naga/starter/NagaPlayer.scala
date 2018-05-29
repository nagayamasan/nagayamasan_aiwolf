package jp.ynu.eis.forest.naga.starter

import java.util

import jp.ynu.eis.forest.naga.roleAgent._
import org.aiwolf.common.data.{Agent, Player, Role, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting, TcpipClient}


object NagaPlayer {
  def main(args: Array[String]): Unit = {

    val serv = new Thread(new Server)
    serv.start()
    //ugly code
    serv.join(1000)
    val nl = new util.ArrayList[NagaPlayer]
    val cl = new util.ArrayList[TcpipClient]
    var i = 0
    while (i < 5) {
      nl.add(new NagaPlayer)
      cl.add(new TcpipClient("localhost", 10000))
      cl.get(i).connect(nl.get(i))

        i += 1

    }

  }
}
class NagaPlayer extends Player {
  private var playerOpt: Option[NagaPersona]  = Option.empty[NagaPersona]

  override def getName: String = {
    "naga"
  }
  override def update(gameInfo: GameInfo): Unit = {
    playerOpt match {
      case Some(player: NagaPersona) =>
        player.update(gameInfo)
      case _ =>
    }
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

    playerOpt = Option(gameInfo.getRole match {
      case Role.SEER => NagaSeer(gameInfo,gameSetting)
      case Role.POSSESSED =>  NagaPossessed(gameInfo,gameSetting)
      case Role.WEREWOLF => NagaWerewolf(gameInfo,gameSetting)
      case Role.VILLAGER => NagaVillager(gameInfo,gameSetting)
      case _ => NagaVillager(gameInfo,gameSetting)
    })

    playerOpt.get.initialize(gameInfo,gameSetting)
  }

  override def dayStart(): Unit = playerOpt match {
    case Some(player: NagaPersona) =>
      player.dayStart()
    case _ =>
  }

  override def talk(): String = {
    playerOpt match {
      case Some(player: NagaPersona) =>
        player.talk()
      case _ => Talk.SKIP
    }
  }

  override def whisper(): String = {
    playerOpt match {
      case Some(werewolf: NagaWerewolf) =>
        werewolf.whisper()
      case _ => Talk.SKIP
    }
  }

  override def vote(): Agent = {
    playerOpt match {
      case Some(player: NagaPersona) =>
        player.vote()
      case _ => null
    }
  }

  override def attack(): Agent = {
    playerOpt match {
      case Some(werewolf: NagaWerewolf) =>
        werewolf.attack()
      case _ => null
    }
  }

  override def divine(): Agent = {
    playerOpt match {
      case Some(seer: NagaSeer) =>
        seer.divine()
      case _ => null
    }
  }

  override def guard(): Agent = {
    playerOpt match {
      //case Some(seer: NagaGuard) =>
        //seer.guard()
      case _ => null
    }
  }

  override def finish(): Unit = playerOpt match {
    case Some(player: NagaPersona) =>
      player.dayStart()
    case _ =>
  }
}