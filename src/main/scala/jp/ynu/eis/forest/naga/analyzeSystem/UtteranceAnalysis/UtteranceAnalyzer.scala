package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.Talk

import scala.collection.mutable
import scala.util.control.Breaks

trait UtteranceAnalyzer {
  spellAnalyze
  seerDetective
  val dm : DialogManager
  val talkanalyzelist: TalkAnalyzeList = dm.taList
  val reCentTalklist = talkanalyzelist.talkList.takeRight(4)

  def getResult: UtteranceResult= {
    val b = new Breaks
    var isQA: Boolean = true

    val myname = dm.gameInfoList.last.getAgent.toString.r
    val question = "？".r

    reCentTalklist.foreach{
      f =>
        if(question.findFirstIn(f.getText).nonEmpty && myname.findFirstIn(f.getText).nonEmpty){
          isQA = true
          b.break
        }
        else{
          isQA = false
        }

    }
    UtteranceResult(dm,isQA,"")

  }
  def spellAnalyze ={
    CaboCha.getResult(dm)
    MeCab.getResult(dm)

  }

  def seerDetective= {
    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r
    reCentTalklist.foreach{
      f =>
        if(kekka.findFirstIn(f.getText).nonEmpty){
          dm.seerList += f.getAgent

      }

    }
  }

  def opponentDetective ={
    //エージェントの正体を見つける

    val kanofact = "\\?".r
    val keldicfact = "なんとなく".r
    val wordwolffact = "白|黒".r
    val mcrefact = "自称占い師".r
    val indigofact = "私は".r

  }

}
