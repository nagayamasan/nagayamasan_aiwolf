package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable

case class NagaWerewolf(var gameInfo: GameInfo, gameSetting: GameSetting) extends NagaPersona {

  override def update(gameInfo: GameInfo): Unit = {
  }

  override def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit = {

  }

  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {
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
    }else{
      return null
    }
  }

  def attack(): Agent = {
    val attackList: mutable.MutableList[Agent] = dm.seerList
    if(attackList.isEmpty){
      return null
    }else if(attackList.size == 1){
      return attackList.head
    }else if(attackList.contains(dm.taList.anaList.resod.kano)){
      return dm.taList.anaList.resod.kano.get
    }else{
      return null
    }
  }
  override def finish(): Unit = {

  }
}
