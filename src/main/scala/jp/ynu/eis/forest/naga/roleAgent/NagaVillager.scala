package jp.ynu.eis.forest.naga.roleAgent

import scala.util.Random
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.{Agent, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.math.random

case class NagaVillager() extends NagaPersona {

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
