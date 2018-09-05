package jp.ynu.eis.forest.naga.analyzeSystem.dialog

import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine.{ResultOfCaboCha, ResultOfMeCab}
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

//既存のTalkに新たな解析結果を持たせたクラス
case class NeoTalk(talk: Talk) {
  val talkList: mutable.MutableList[Talk] = mutable.MutableList.empty[Talk]
  val allAnalyzeList  = new ConclusionOfAnalyze
  var isQuestion: Boolean = false //質問文かどうか判断


  def getDay: Int ={
    talk.getDay
  }
  def getIdx: Int ={
    talk.getIdx
  }
  def getTurn: Int ={
    talk.getTurn
  }
  def getAgent: Agent ={
    talk.getAgent
  }
  def getText: String={
    talk.getText
  }
  def thisIsQuestion ={
    isQuestion
  }
  def setMeCabData: Unit ={
    MeCab.setResult(talk, allAnalyzeList.resultofmecab)
  }
  def getMeCabData: ResultOfMeCab={
    allAnalyzeList.resultofmecab
  }
  def setCaboChaData: Unit ={
    CaboCha.setResult(talk, allAnalyzeList.resultofcabocha)
  }
  def getCaboChaData: ResultOfCaboCha={
    allAnalyzeList.resultofcabocha
  }

    
    
}
