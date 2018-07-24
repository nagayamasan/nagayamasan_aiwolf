package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab, OpponentDetective}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.Talk

import scala.collection.mutable
import scala.util.control.Breaks

trait UtteranceAnalyzer {
  val dm : DialogManager
  val talkanalyzelist: TalkAnalyzeList = dm.taList
  val reCentTalklist = talkanalyzelist.talkList.takeRight(4)

  if(dm.taList.talkList.nonEmpty){
    spellAnalyze
    seerDetective
  }

  def getResult: UtteranceResult= {
    val myname = dm.gameInfoList.last.getAgent.toString
    val question = """\?""".r
    val isQA = reCentTalklist.exists(f => question.findFirstIn(f.getText).nonEmpty && f.getText.contains(">>" + myname))
    if(dm.taList.anaList.resod.kanoList.nonEmpty){
      UtteranceResult(dm,isQA,dm.taList.anaList.resod.kanoList.last.toString)
    }
    UtteranceResult(dm,isQA,"")

  }
  def spellAnalyze ={
      CaboCha.getResult(dm)
      MeCab.getResult(dm)
      OpponentDetective.getEnemyName(dm)

  }


  def seerDetective= {
    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r
    if (reCentTalklist != null) {
      reCentTalklist.foreach{
        f =>
          if(kekka.findFirstIn(f.getText).nonEmpty){
            dm.seerList += f.getAgent

          }
      }
    }
  }

}
