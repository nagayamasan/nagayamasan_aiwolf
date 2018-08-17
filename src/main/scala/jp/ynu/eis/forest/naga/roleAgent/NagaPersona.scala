package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.vote.VoteDecider
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.{GameInfo, GameSetting}

import scala.collection.mutable

trait NagaPersona {
 /* val name : String = "naga"
  val dm = new DialogManager
  var gameInfo: GameInfo
  var gameSetting: GameSetting
  val TURN_AGENT_ATERU_NUMBER = 3
  val VOTE_DECIDED_TURN = 8
  val agentList: mutable.MutableList[Agent] = dm.agentListChange(gameInfo.getAliveAgentList).filter(el => gameInfo.getAgent != el)*/
  val dm = new DialogManager

  //gamesettingはdmに入れてない！！！
  def init(gameInfo: GameInfo, gameSetting: GameSetting) : Unit={
    dm.gameInfoList += gameInfo
  }


  def update(gameInfo: GameInfo): Unit = {
    dm.gameInfoList += gameInfo
    dm.taList.collecting(gameInfo)
  }


  def dayStart(): Unit = {
    dm.resetTurn
  }

  def talk(): String = {
    /*//println("speak")
    dm.addTurn

    if(gameInfo.getDay == 1 && dm.getTurn == 1){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      return "おはよう。占い師いる？"

    }
    else if (gameInfo.getDay == 2 && dm.getTurn == 1){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      return "おはよう。二日目の朝が来てしまった。"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == TURN_AGENT_ATERU_NUMBER){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      OpponentDetective.setEnemyname(dm)
      var sentence = ""
      if(dm.taList.anaList.resod.kano.nonEmpty && dm.callkano.get != gameInfo.getAgent){
        sentence = dm.taList.anaList.resod.kano.get + "は誰かさんみたいな話し方をされますね。"
      }
      if(dm.taList.anaList.resod.keldic.nonEmpty){
        if(sentence.isEmpty){
          sentence = dm.taList.anaList.resod.keldic.get + "はいつぞやのkeldicさんみたいですね。"
        }else{
          sentence = dm.taList.anaList.resod.keldic.get + "はkeldicさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.mcre.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.mcre.get + "はひょっとするとmcreさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.mcre.get + "はmcreさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.wordWolf.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.wordWolf.get + "はもしかしてwordWolfさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はwordWolfさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.udon.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.wordWolf.get + "はもしかしてUdonさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はUdonさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.indigo.nonEmpty && dm.taList.anaList.resod.indigo.get != gameInfo.getAgent ){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.indigo.get + "はどこかで聞いたことある話し方をしますね。"
        }else{
          sentence = dm.taList.anaList.resod.indigo.get + "はどこかで聞いたことある話し方をしますね。" + sentence
        }
      }
      return sentence

    }
    else if(gameInfo.getDay == 0){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      if(dm.getTurn == 1) {
        "お手柔らかにお願いします。"
      }else{
        Talk.OVER
      }
    }
      //dm.taList.anaList.resod.kano.getがnullになるとやばい?
    else if(dm.taList.anaList.resod.kano.nonEmpty && dm.seerList.contains(dm.taList.anaList.resod.kano.get)){
      dm.gameInfoList += gameInfo
      dm.taList.collecting(gameInfo)

      return dm.callkano + "は真か狼だよ"

    }
    else if(dm.talkListChange(gameInfo.getTalkList).last.getTurn == VOTE_DECIDED_TURN){
      Talk.OVER
    }
    else{

    }*/
    PipeLine(dm).getOutput
  }

  def vote(): Agent = {
    VoteDecider(dm).getVoteAgent
  }

  def finish(): Unit
}
