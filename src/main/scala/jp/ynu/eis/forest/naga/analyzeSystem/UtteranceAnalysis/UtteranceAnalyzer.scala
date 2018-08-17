package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.OpponentMetaData._
import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab, OpponentDetective}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds._
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

trait UtteranceAnalyzer {
  val dm : DialogManager
  val gameInfo : GameInfo = dm.gameInfoList.last
  val recentTalkList: mutable.MutableList[Talk] = dm.taList.talkList.filter(_.getTurn == dm.taList.talkList.last.getTurn)
  var mind: SituationMind = RandomTalk

  if(dm.taList.talkList.nonEmpty){
    spellAnalyze()
    seerDetective()

  }

  def analyze() : Unit


  def getResult: UtteranceResult= {
    val myname = dm.gameInfoList.last.getAgent.toString
    val question = dm.questionRegex
    //val isQA = recentTalkList.exists(f => question.findFirstIn(f.getText).nonEmpty && f.getText.contains(">>" + myname))
    val isQA = false
    if(!isQA && mind == RandomTalk){
      gomi
    }
    UtteranceResult(dm, isQA, mind)

  }
  def spellAnalyze() : Unit ={
      CaboCha.getResult(dm)
      MeCab.getResult(dm)
      OpponentDetective.collectEnemyName(dm)

  }


  def seerDetective() : Unit= {
    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r
    if (recentTalkList.nonEmpty) {
      recentTalkList.foreach{
        f =>
          if(seer.findFirstIn(f.getText).nonEmpty){
            dm.seerList += f.getAgent

          }
          else if(kekka.findFirstIn(f.getText).nonEmpty){
            dm.seerList += f.getAgent
          }
      }
    }
  }

  private def gomi :Unit ={
    //println("speak")
    if(gameInfo.getDay == 1 && dm.getTurn == 1){

     mind = SeerComeon

    }
    else if (gameInfo.getDay == 2 && dm.getTurn == 1){
      mind = Greeting(gameInfo.getDay)
      //return "おはよう。二日目の朝が来てしまった。"
    }
    else if(gameInfo.getDay == 1 && dm.getTurn == dm.TURN_AGENT_ATERU_NUMBER){
      OpponentDetective.setEnemyname(dm)
      //var sentence = ""
      if(dm.taList.anaList.resod.kano.nonEmpty && dm.callkano.get != gameInfo.getAgent){
       mind = OpponentJudge(dm.callkano.get, Kanolab)
        // sentence = dm.taList.anaList.resod.kano.get + "は誰かさんみたいな話し方をされますね。"
      }
      if(dm.taList.anaList.resod.keldic.nonEmpty){
        mind = OpponentJudge(dm.callkeldic.get, Keldic)
        /*if(sentence.isEmpty){
          //sentence = dm.taList.anaList.resod.keldic.get + "はいつぞやのkeldicさんみたいですね。"
        }else{
          sentence = dm.taList.anaList.resod.keldic.get + "はkeldicさん、" + sentence
        }*/
      }
      if(dm.taList.anaList.resod.mcre.nonEmpty){
        mind = OpponentJudge(dm.callmcre.get, Mcre)
        /*if(sentence == ""){
          sentence = dm.taList.anaList.resod.mcre.get + "はひょっとするとmcreさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.mcre.get + "はmcreさん、" + sentence
        }*/
      }
      if(dm.taList.anaList.resod.wordWolf.nonEmpty){
        mind = OpponentJudge(dm.callwordWlof.get, Wordwolf)
      }
       /* if(sentence == ""){
          sentence = dm.taList.anaList.resod.wordWolf.get + "はもしかしてwordWolfさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はwordWolfさん、" + sentence
        }
      }*/
      if(dm.taList.anaList.resod.udon.nonEmpty){
        mind = OpponentJudge(dm.taList.anaList.resod.udon.get, Udon)
        /*if(sentence == ""){
          sentence = dm.taList.anaList.resod.wordWolf.get + "はもしかしてUdonさんですか。"
        }else{
          sentence = dm.taList.anaList.resod.wordWolf.get + "はUdonさん、" + sentence
        }*/
      }
      if(dm.taList.anaList.resod.indigo.nonEmpty && dm.taList.anaList.resod.indigo.get != gameInfo.getAgent ){
        mind = OpponentJudge(dm.taList.anaList.resod.indigo.get, Indigo)
        /*if(sentence == ""){
          sentence = dm.taList.anaList.resod.indigo.get + "はどこかで聞いたことある話し方をしますね。"
        }else{
          sentence = dm.taList.anaList.resod.indigo.get + "はどこかで聞いたことある話し方をしますね。" + sentence
        }*/
      }
      //return sentence

    }
    else if(gameInfo.getDay == 0){

      if(dm.getTurn == 1) {
        mind = Greeting(0)
       // "お手柔らかにお願いします。"
      }else{
       mind = TalkOver
        // Talk.OVER
      }
    }
    //dm.taList.anaList.resod.kano.getがnullになるとやばい?
    else if(dm.taList.anaList.resod.kano.nonEmpty && dm.seerList.contains(dm.taList.anaList.resod.kano.get)){

      mind = KanoMeta(dm.callkano.get)
      //dm.callkano + "は真か狼だよ"

    }
    else if(dm.talkListChange(gameInfo.getTalkList).last.getTurn == dm.VOTE_DECIDED_TURN){
      mind = TalkOver
    }

  }

}
