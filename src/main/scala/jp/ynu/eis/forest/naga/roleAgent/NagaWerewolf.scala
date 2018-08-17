package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable

case class NagaWerewolf() extends NagaPersona {
  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
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

    super.vote()
  }

  def attack(): Agent = {
    val attackList: mutable.MutableList[Agent] = dm.seerList
    if(attackList.isEmpty){
      null
    }else if(attackList.size == 1){
      attackList.head
    }else if(dm.callkano.isDefined && attackList.contains(dm.callkano.get)){
      dm.taList.anaList.resod.kano.get
    }else{
      null
    }
  }
  override def finish(): Unit = {

  }
}
