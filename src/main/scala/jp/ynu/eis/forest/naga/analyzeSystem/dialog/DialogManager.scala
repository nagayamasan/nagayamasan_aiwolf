package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import java.util

import jp.ynu.eis.forest.naga.OpponentMetaData.OpponentData
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine.ResultOfOppDet
import org.aiwolf.common.data.{Agent, Judge, Role, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable

class DialogManager{

  val gameInfoList = mutable.MutableList.empty[GameInfo]
  val neoTalkList = mutable.MutableList.empty[NeoTalk]
  val seerList = mutable.MutableList.empty[Agent]
  val wolfList = mutable.MutableList.empty[Agent]
  val possList = mutable.MutableList.empty[Agent]

  val questionRegex = """？|\?""".r
  val nameDetectList = mutable.MutableList.empty[OpponentData]
  val resultOfOpp = new ResultOfOppDet
  val TURN_AGENT_ATERU_NUMBER = 3
  val VOTE_DECIDED_TURN = 8
  var possdecFlag = false//人狼が狂人を把握したことを発言したかどうかのフラグ
  var turn = 0

  val divineList: mutable.MutableList[Judge] = mutable.MutableList.empty[Judge]
  var agentList: mutable.MutableList[Agent] = mutable.MutableList.empty[Agent]




  def init(gameInfo: GameInfo) :Unit={
    gameInfoList.clear()
    gameInfoList += gameInfo
    possdecFlag = false//人狼が狂人を把握したことを発言したかどうかのフラグ
    agentList= agentListChange(gameInfoList.last.getAliveAgentList).filter(el => gameInfoList.last.getAgent != el)
    divineList.clear()
    nameDetectList.clear()
  }
  def update(gameInfo: GameInfo) :Unit ={
    gameInfoList += gameInfo
    setNeoTalkList(gameInfo)
    agentList= agentListChange(gameInfoList.last.getAliveAgentList).filter(el => gameInfoList.last.getAgent != el)
  }
  def setNeoTalkList(gameInfo: GameInfo)= {
    //val newGetTalkSize = agentListChange(gameInfo.getAliveAgentList).size - 1  自分を除く直近のトークリストの数
    if (!gameInfo.getTalkList.isEmpty){
      val recentTalkList: mutable.MutableList[Talk] = talkListChange(gameInfo.getTalkList).filter(_.getTurn == talkListChange(gameInfo.getTalkList).last.getTurn)
      recentTalkList.foreach {
        recentTalk =>
          neoTalkList += NeoTalk(recentTalk)
      }
    }
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
    val agentNameList = mutable.MutableList.empty[Agent]
    agentNameList += resultOfOpp.kano.get
    agentNameList += resultOfOpp.keldic.get
    agentNameList += resultOfOpp.mcre.get
    agentNameList += resultOfOpp.wordWolf.get
    agentNameList += resultOfOpp.indigo.get

    agentNameList
  }

  def getKano: Option[Agent] ={
    resultOfOpp.kano
  }
  def getKeldic: Option[Agent] ={
    resultOfOpp.keldic
  }
  def getMcre: Option[Agent] ={
    resultOfOpp.mcre
  }
  def getWordWolf: Option[Agent] ={
    resultOfOpp.wordWolf
  }
  def getUdon: Option[Agent] ={
    resultOfOpp.udon
  }
  def getRosen: Option[Agent] ={
    resultOfOpp.rosen
  }
  def getFuku: Option[Agent] ={
    resultOfOpp.fuku
  }
  def getAries: Option[Agent] ={
    resultOfOpp.aries
  }
  def getCains: Option[Agent]={
    resultOfOpp.cains
  }
  def getIndigo: Option[Agent]={
    resultOfOpp.indigo
  }
}

