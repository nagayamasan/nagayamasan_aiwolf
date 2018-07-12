package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.UtteranceResult
import org.aiwolf.common.data.{Talk}

import scala.collection.mutable

trait UtteranceAnalyzer {
  val dm : DialogManager
  val tl: mutable.MutableList[Talk] = dm.taList.talkList
  def getResult: UtteranceResult = {

    UtteranceResult(tl, true, "")
  }
  def spellAnalyze ={
    CaboCha.getResult(dm)
    MeCab.getResult(dm)

  }

  def seerDetective= {
    //val SeerLikeSpell = tl.last.getText

    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r

    if(kekka.findFirstIn(tl.last.getText) != None){
      dm.seerList += tl.head.getAgent

    }
  }
}
