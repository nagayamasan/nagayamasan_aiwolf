package jp.ynu.eis.forest.naga.starter

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.roleAgent._
import org.aiwolf.common.data.{Agent, Player, Role, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting, TcpipClient}


object NagaPlayer {

  def clientStart(clientNumber : Int) :Unit={
    //テキストファイルの読み込みをコネクション確立前に行う
    println("file loading...")
    OpponentDetective.inputTextResources()
    println("success.")
    val nagaPlayers = new util.ArrayList[NagaPlayer]
    val tcpipClients = new util.ArrayList[TcpipClient]
    var i = 0
    while (i < clientNumber) {
      nagaPlayers.add(new NagaPlayer)
      //hostをいじる
      tcpipClients.add(new TcpipClient("localhost", 10000))
      tcpipClients.get(i).connect(nagaPlayers.get(i))

      i += 1

    }
  }


  def main(args: Array[String]): Unit = {

    clientStart(1)

  }
}
class NagaPlayer extends Player {
  private var playerOpt: Option[NagaPersona]  = Option.empty[NagaPersona]

  override def getName: String = {
    "forstsan"
  }
  override def update(gameInfo: GameInfo): Unit = {
    println("***update***")
    playerOpt match {
      case Some(player: NagaPersona) =>
        player.update(gameInfo)
      case _ =>
    }
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

    playerOpt = Option(gameInfo.getRole match {
      case Role.SEER => NagaSeer()
      case Role.POSSESSED =>  NagaPossessed()
      case Role.WEREWOLF => NagaWerewolf()
      case Role.VILLAGER => NagaVillager()
      case _ => NagaVillager()
    })
    println(s"init : I(${gameInfo.getAgent}) am ${gameInfo.getRole}.")
    playerOpt.get.init(gameInfo,gameSetting)
  }

  override def dayStart(): Unit = {
    println("***day start***")
    playerOpt match {
    case Some(player: NagaPersona) =>
      player.dayStart()
    case _ =>
  }
  }

  override def talk(): String = {
    playerOpt match {
      case Some(player: NagaPersona) =>
        val ret = player.talk()
        println(s"talk : $ret")
        ret
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
        val ret = player.vote()
        println(s"vote : $ret")
        ret
      case _ => null
    }
  }

  override def attack(): Agent = {
    playerOpt match {
      case Some(werewolf: NagaWerewolf) =>
        val ret = werewolf.attack()
        println(s"attack : $ret")
        ret
      case _ => null
    }
  }

  override def divine(): Agent = {
    playerOpt match {
      case Some(seer: NagaSeer) =>
        val ret = seer.divine()
        println(s"divine : $ret")
        ret
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

  override def finish(): Unit = {
    println("***game finished***")
    playerOpt match {
      case Some(player: NagaPersona) =>
        player.dayStart()
      case _ =>
    }
  }
}
