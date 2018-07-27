package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent
import org.aiwolf.common.net.{GameInfo, GameSetting}

trait NagaPersona {
  val name : String = "naga"
  val dm = new DialogManager
  var gameInfo: GameInfo
  var gameSetting: GameSetting
  val TURN_AGENT_ATERU_NUMBER = 3


  def update(gameInfo: GameInfo): Unit = {
    this.gameInfo = gameInfo
  }

  def initialize(gameInfo: GameInfo, gameSetting: GameSetting): Unit

  def dayStart(): Unit = {
    dm.resetTurn
  }

  def talk(): String = {

    dm.addTurn
    if(gameInfo.getDay == 1 && dm.turn == TURN_AGENT_ATERU_NUMBER){
      OpponentDetective.setEnemyname(dm)
      var sentence = ""
      if(dm.taList.anaList.resod.kano.nonEmpty){
        sentence = dm.taList.anaList.resod.kano.get + "はkanoさんですね。"
      }
      if(dm.taList.anaList.resod.keldic.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.keldic.get + "はkeldicさんですね。"
        }else{
          sentence = dm.taList.anaList.resod.keldic.get + "はkeldicさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.mcre.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.mcre.get + "はmcreさんですね。"
        }else{
          sentence = dm.taList.anaList.resod.mcre.get + "はmcreさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.wordWolf.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.wordWolf.get + "はwordWolfさんですね。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はwordWolfさん、" + sentence
        }
      }
      if(dm.taList.anaList.resod.indigo.nonEmpty){
        if(sentence == ""){
          sentence = dm.taList.anaList.resod.indigo.get + "はindigoさんですね。"
        }else{
          sentence = dm.taList.anaList.resod.indigo.get + "はindigoさん、" + sentence
        }
      }
      return sentence
    }
    new PipeLine(gameInfo, dm).getOutput
  }

  def vote(): Agent

  def finish(): Unit
}
