package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import java.util

import jp.ynu.eis.forest.naga.OpponentMetaData.OpponentData
import org.aiwolf.common.data.{Agent, Judge, Role, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting}
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.collection.JavaConverters._

class DialogManager{

  val gameInfoList = mutable.MutableList.empty[GameInfo]
  val taList = new TalkAnalyzeList
  val seerList = mutable.MutableList.empty[Agent]
  val wolfList = mutable.MutableList.empty[Agent]
  val possList = mutable.MutableList.empty[Agent]
  val questionRegex = """？|\?""".r
  var turn = 0
  val nameDetectList = mutable.MutableList.empty[OpponentData]

  val TURN_AGENT_ATERU_NUMBER = 3
  val VOTE_DECIDED_TURN = 8
  var possdecFlag = false//人狼が強靭を把握したことを発言したかどうかのフラグ
  var agentList: mutable.MutableList[Agent] = mutable.MutableList.empty[Agent]

  val divineList: mutable.MutableList[Judge] = mutable.MutableList.empty[Judge]


  def init(gameInfo: GameInfo) :Unit={
    gameInfoList.clear()
    gameInfoList += gameInfo
    possdecFlag = false//人狼が強靭を把握したことを発言したかどうかのフラグ
    agentList= agentListChange(gameInfoList.last.getAliveAgentList).filter(el => gameInfoList.last.getAgent != el)
    divineList.clear()
    nameDetectList.clear()
  }
  def update(gameInfo: GameInfo) :Unit ={
    gameInfoList += gameInfo
    taList.collecting(gameInfo)
    agentList= agentListChange(gameInfoList.last.getAliveAgentList).filter(el => gameInfoList.last.getAgent != el)
  }

  def resetTurn: Unit = {
    turn = 0
  }

  def addTurn: Unit = {
    turn += 1
  }

  def getTurn: Int = {
    turn
  }

  def agentListChange(javalist: util.List[Agent]): mutable.MutableList[Agent]={
    val mutableList = mutable.MutableList.empty[Agent]
    javalist.forEach{
      element =>
        mutableList += element
    }
    mutableList
  }
  def talkListChange(javalist: util.List[Talk]): mutable.MutableList[Talk]={
    val mutableList = mutable.MutableList.empty[Talk]
    javalist.forEach{
      element =>
        mutableList += element
    }
    mutableList
  }
  def roleListChange(javalist: util.List[Role]): mutable.MutableList[Role]= {
    val mutableList = mutable.MutableList.empty[Role]
    javalist.forEach {
      element =>
        mutableList += element
    }
    mutableList
  }

  def listedAgentName: mutable.MutableList[Agent] ={
    val agnmList = mutable.MutableList.empty[Agent]
    agnmList += taList.anaList.resod.kano.get
    agnmList += taList.anaList.resod.keldic.get
    agnmList += taList.anaList.resod.mcre.get
    agnmList += taList.anaList.resod.wordWolf.get
    agnmList += taList.anaList.resod.indigo.get

    return agnmList
  }

  def callMeCab ={
    val word = taList.anaList.resm.wordData
    val wordim = taList.anaList.resm.wordimData
  }
  def callCabocha={
    val word = taList.anaList.resc.wordData
    val wordim = taList.anaList.resc.wordimData

  }
  def callkano: Option[Agent] ={
    taList.anaList.resod.kano

  }
  def callkeldic: Option[Agent] = {
    taList.anaList.resod.keldic

  }
  def callmcre: Option[Agent] ={
    taList.anaList.resod.mcre

  }
  def callwordWlof: Option[Agent] ={
    taList.anaList.resod.wordWolf

  }

}

