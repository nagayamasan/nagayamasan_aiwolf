package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable

case class NagaWerewolf(var gameInfo: GameInfo, var gameSetting: GameSetting) extends NagaPersona {
  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {

    if(gameInfo.getDay == 2 && dm.possList.nonEmpty){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)
      dm.getTurn

      return "狂人把握した"
    }
    super.talk()

  }

  def whisper(): String = {
    "kusa"
  }

  override def vote(): Agent = {
    val attackList: mutable.MutableList[Agent] = dm.seerList
    if(attackList.isEmpty){
      return null
    }else if(attackList.size == 1){
      return attackList.head
    }else if(attackList.contains(dm.taList.anaList.resod.kano)){
      return dm.taList.anaList.resod.kano.get

    }else if(dm.gameInfoList.last.getDay == 2 && dm.possList.nonEmpty){
      agentList.filter(_ != dm.possList.head).last

    } else{
      return null
    }
  }

  def attack(): Agent = {
    val attackList: mutable.MutableList[Agent] = dm.seerList
    if(attackList.isEmpty){
      return null
    }else if(attackList.size == 1){
      return attackList.head
    }else if(attackList.contains(dm.callkano)){
      return dm.taList.anaList.resod.kano.get
    }else{
      return null
    }
  }
  override def finish(): Unit = {

  }
}
