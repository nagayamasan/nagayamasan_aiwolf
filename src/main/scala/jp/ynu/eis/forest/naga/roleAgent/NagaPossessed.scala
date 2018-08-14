package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting, JudgeToSend}

import scala.collection.mutable
import scala.util.Random

case class NagaPossessed(var gameInfo: GameInfo, var gameSetting: GameSetting) extends NagaPersona {
  val blackTarget = Random.shuffle(agentList).head

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

    if(gameInfo.getDay == 1 && dm.turn == 0){
      dm.addTurn
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      return "私は占い師です"

    }else if(gameInfo.getDay == 1 && dm.getTurn == 1){
      dm.addTurn
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      return "誠に残念ながら" + blackTarget + "は" + Random.shuffle(judgeList).head + "でした。"
    }else if(gameInfo.getDay == 2 && dm.turn == 0){
      dm.addTurn
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      return "あ、どーも僕が狂人です。"
    }
    super.talk()
  }

  override def vote(): Agent = {
    val votelist: mutable.MutableList[Agent] = dm.seerList.filter(_ != gameInfo.getAgent)

    if(votelist.isEmpty){
      blackTarget
    }
    else if(dm.gameInfoList.last.getDay == 2 && dm.wolfList.nonEmpty){
      agentList.filter(_ != dm.wolfList.head).last

    }
    else votelist.last

  }
  override def finish(): Unit = {


  }
}
