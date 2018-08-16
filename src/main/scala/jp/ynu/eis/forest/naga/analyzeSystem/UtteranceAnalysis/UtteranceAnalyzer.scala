package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab, OpponentDetective}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.UtteranceResult

trait UtteranceAnalyzer {
  val dm : DialogManager
  val talkanalyzelist: TalkAnalyzeList = dm.taList
  val reCentTalklist = talkanalyzelist.talkList.filter(_.getTurn == dm.taList.talkList.last.getTurn)

  if(dm.taList.talkList.nonEmpty){
    spellAnalyze
    seerDetective

  }

  def getResult: UtteranceResult= {
    val myname = dm.gameInfoList.last.getAgent.toString
    val question = dm.questionRegex
    val isQA = reCentTalklist.exists(f => question.findFirstIn(f.getText).nonEmpty && f.getText.contains(">>" + myname))
    if(dm.taList.anaList.resod.kanoList.nonEmpty) {
      UtteranceResult(dm, isQA, dm.taList.anaList.resod.kanoList.last.toString)
    }
    else{
      UtteranceResult(dm, isQA, "")
    }

  }
  def spellAnalyze ={
      CaboCha.getResult(dm)
      MeCab.getResult(dm)
      OpponentDetective.collectEnemyName(dm)

  }


  def seerDetective= {
    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r
    if (reCentTalklist.nonEmpty) {
      reCentTalklist.foreach{
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

}
