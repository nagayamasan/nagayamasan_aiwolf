package jp.ynu.eis.forest.naga.vote

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.{Agent, Role, Species}

import scala.collection.mutable
import scala.util.Random

case class VoteDecider(dm : DialogManager) {

  var voteAgentOpt: Option[Agent]  = Option.empty[Agent]

  voteAgentOpt = Option(dm.gameInfoList.last.getRole match {
    case Role.SEER => seerVoteAction
    case Role.POSSESSED =>  possessedVoteAction
    case Role.WEREWOLF => werewolfVoteAction
    case Role.VILLAGER => villagerVoteAction
    case _ => villagerVoteAction
  })


  def seerVoteAction : Agent ={
    val divinedWerewolfList = dm.divineList.filter(_.getResult == Species.WEREWOLF)
    if (divinedWerewolfList.nonEmpty) {
      return divinedWerewolfList.head.getTarget
    }
    val candidateAgentList :mutable.MutableList[Agent]= dm.agentList
    val divinedHumanList = dm.divineList.filter(_.getResult == Species.HUMAN)


    val a: mutable.MutableList[Agent] = candidateAgentList diff divinedHumanList
    //nullの可能性あり
    a.get(Random.nextInt(candidateAgentList.size)).get
  }
  def villagerVoteAction : Agent ={
    val candidateAgentList = dm.agentListChange(dm.gameInfoList.last.getAliveAgentList).filter(_ != dm.gameInfoList.last.getAgent)
    dm.seerList.foreach{
      f =>
        candidateAgentList.filter(ag => ag== f)
    }
    if(candidateAgentList.nonEmpty) {
      dm.seerList.foreach{
        f =>
          candidateAgentList.filter(ag => ag== f)

      }
      Random.shuffle(candidateAgentList).head

    }

    else{
      null

    }
  }
  def werewolfVoteAction : Agent ={
    val attackList: mutable.MutableList[Agent] = dm.seerList
    if(attackList.isEmpty){
     null
    }else if(attackList.size == 1){
      attackList.head
    }//kanoメタ
    else if(dm.taList.anaList.resod.kano.isDefined && attackList.contains(dm.taList.anaList.resod.kano.get)){
      dm.taList.anaList.resod.kano.get

    }else if(dm.gameInfoList.last.getDay == 2 && dm.possList.nonEmpty){
      dm.agentList.filter(_ != dm.possList.head).last

    } else{
      null
    }
  }
  def possessedVoteAction : Agent ={
    val votelist: mutable.MutableList[Agent] = dm.seerList.filter(_ != dm.gameInfoList.last.getAgent)

    if(votelist.isEmpty){
      Random.shuffle(dm.agentList).last
    }
    else if(dm.gameInfoList.last.getDay == 2 && dm.wolfList.nonEmpty){
      dm.agentList.filter(_ != dm.wolfList.head).last

    }
    else{
      votelist.last
    }
  }

  def getVoteAgent : Agent={
    voteAgentOpt.get
  }
}
