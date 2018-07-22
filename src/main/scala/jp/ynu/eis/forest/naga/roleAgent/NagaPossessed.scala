package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting, JudgeToSend}

case class NagaPossessed(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {
  val dm = new DialogManager
  //val blackSeer:Agent =
  override def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {
    //println("おはよう、早速だけど僕が占い師だよ")

  }

  override def talk(): String = {
    new PipeLine(gameInfo, dm).getOutput
  }

  override def vote(): Agent = {
   val votelist = dm.seerList.filter(_ != gameInfo.getAgent)
    if(votelist.isEmpty){
      null
    }
    else votelist.last

  }
  override def finish(): Unit = {


  }
}
