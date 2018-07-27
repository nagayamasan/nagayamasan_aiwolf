package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

trait NagaPersona {
  val name : String = "naga"
  val dm = new DialogManager
  var gameInfo: GameInfo
  var gameSetting: GameSetting
  val TURN_AGENT_ATERU_NUMBER = 3


  def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit

  def dayStart(): Unit = {
    dm.resetTurn
  }

  def talk(): String = {
    dm.addTurn
    if(dm.getTurn == TURN_AGENT_ATERU_NUMBER){
      //OpponentDetective.setEnemyname(dm)
      //dm.taList.anaList.resod.keldicList.last.toString


    }
    new PipeLine(gameInfo, dm).getOutput
  }

  def vote(): Agent

  def finish(): Unit
}
