package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting, JudgeToSend}

import scala.collection.mutable
import scala.util.Random

case class NagaPossessed() extends NagaPersona {

  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
  }



  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {

    super.talk()
  }

  override def vote(): Agent = {

    super.vote()
  }
  override def finish(): Unit = {


  }
}
