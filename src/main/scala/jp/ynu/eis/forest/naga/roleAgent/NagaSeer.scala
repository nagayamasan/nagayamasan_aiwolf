package jp.ynu.eis.forest.naga.roleAgent


import org.aiwolf.common.data.{Agent, Judge, Role, Species}
import org.aiwolf.common.net.{GameInfo, GameSetting}


import scala.util.Random

case class NagaSeer() extends NagaPersona {



  override def update(gameInfo: GameInfo): Unit = {
    super.update(gameInfo)
    if (gameInfo.getDivineResult != null) {
      dm.divineList += gameInfo.getDivineResult
    }
  }



  override def dayStart(): Unit = {
    super.dayStart()
  }

  override def talk(): String = {

    super.talk()

  }

  override def vote: Agent = {

    super.vote()
  }

  def divine(): Agent = {
    val candidateAgentList = dm.agentList

    if(candidateAgentList.nonEmpty){
      Random.shuffle(candidateAgentList).head

    }else{
      null

    }

  }

  override def finish(): Unit = {

  }
}
