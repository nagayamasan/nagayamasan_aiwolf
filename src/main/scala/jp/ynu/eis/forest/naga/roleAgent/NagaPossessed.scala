package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting, JudgeToSend}

import scala.collection.mutable
import scala.util.Random

case class NagaPossessed(var gameInfo: GameInfo, var gameSetting: GameSetting) extends NagaPersona {
  val agentList = dm.agentListChange(gameInfo.getAliveAgentList).filter(el => gameInfo.getAgent != el)

  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {
    val judgeList = mutable.MutableList("人狼","黒")

    if(gameInfo.getDay == 1 && dm.turn == 1){
      dm.addTurn
      return "私は占い師です"

    }else if(gameInfo.getDay == 1 && dm.getTurn == 2){
      dm.addTurn
      return "占いの結果" + Random.shuffle(agentList).head + "は" + Random.shuffle(judgeList).head + "でした。"
    }else if(gameInfo.getDay == 2 && dm.turn == 1){
      dm.addTurn
      return "あ、どーも僕が狂人です。"
    }
    super.talk()
  }

  override def vote(): Agent = {
   val votelist = dm.seerList.filter(_ != gameInfo.getAgent)
    if(votelist.isEmpty){
      Random.shuffle(agentList).head
    }
    else votelist.last

  }
  override def finish(): Unit = {


  }
}
