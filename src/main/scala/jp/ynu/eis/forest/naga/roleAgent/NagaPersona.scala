package jp.ynu.eis.forest.naga.roleAgent

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.PipeLine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.{Agent, Talk}
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
    //println("speak")
    dm.addTurn
    if(gameInfo.getDay == 1 && dm.turn == TURN_AGENT_ATERU_NUMBER){
      OpponentDetective.setEnemyname(dm)
      var sentence = ""
      if(dm.taList.anaList.resod.kano.nonEmpty && dm.callkano.get != gameInfo.getAgent){
        sentence = dm.taList.anaList.resod.kano.get + "はkanoさんみたいな話し方をされますね。"
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
          sentence = dm.taList.anaList.resod.wordWolf.get + "はもしかしてwordWolfさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はwordWolfさん、" + sentence
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

    }else if(gameInfo.getDay == 0){
      if(dm.getTurn == 1) {
        "お手柔らかにお願いします。"
      }else{
        Talk.OVER
      }
    }
    else if(dm.taList.anaList.resod.kano.nonEmpty && dm.seerList.contains(dm.taList.anaList.resod.kano)){
      dm.callkano + "は真か狼だよ"

    }
    else {
      new PipeLine(gameInfo, dm).getOutput

    }
  }

  def vote(): Agent

  def finish(): Unit
}
