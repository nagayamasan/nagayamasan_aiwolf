package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting}



trait NagaPersona {

  val dm = new DialogManager

  //gamesettingはdmに入れてない！！！
  def init(gameInfo: GameInfo, gameSetting: GameSetting) : Unit={
    dm.init(gameInfo)
  }


  def update(gameInfo: GameInfo): Unit = {
    dm.update(gameInfo)
  }


  def dayStart(): Unit = {
    dm.resetTurn
  }

  def talk(): String = {

    PipeLine(dm).getOutput
  }

  def vote(): Agent = {
    VoteDecider(dm).getVoteAgent
  }

  def finish(): Unit
}
