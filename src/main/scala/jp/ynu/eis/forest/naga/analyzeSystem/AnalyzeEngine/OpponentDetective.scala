package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent

import scala.collection.{immutable, mutable}
import scala.io.Source

object OpponentDetective{

  val seq: mutable.MutableList[(Char, Char)] = mutable.MutableList.empty[(Char, Char)]
  val sourceDir = "resource"

  val KanoSource = Source.fromFile(s"${sourceDir}/kanofact.txt")
  val KeldicSource = Source.fromFile(s"${sourceDir}/keldicfact.txt")
  val McreSource = Source.fromFile(s"${sourceDir}/mcrefact.txt")
  val WordWolfSource = Source.fromFile(s"${sourceDir}/wordwolffact.txt")

  val kanoLines = KanoSource.getLines
  val keldicLines = KeldicSource.getLines
  val mcreLines = McreSource.getLines
  val wordwolfLines = WordWolfSource.getLines

  def getEnemyName(dm: DialogManager): Unit ={
    val tlList = dm.taList.talkList.takeRight(4)
    val odList = dm.taList.anaList.resod

    tlList.foreach{
      tl =>
        if(tl.isOver || tl.isSkip){
          //no action

        } else if(kanoLines.contains(tl.getText)) {
          dm.taList.anaList.resod.kanoList += tl.getAgent

        } else if(keldicLines.contains(tl.getText)) {
            dm.taList.anaList.resod.keldicList += tl.getAgent

        } else if(mcreLines.contains(tl.getText)) {
          dm.taList.anaList.resod.mcreList += tl.getAgent

        } else if(wordwolfLines.contains(tl.getText)) {
          dm.taList.anaList.resod.wordwolfList += tl.getAgent

        } else{
          dm.taList.anaList.resod.indigoList += tl.getAgent
        }
    }
  }












//  lines.foreach{
//    f =>
//      var slide = f.tail
//      while(slide.nonEmpty){
//        var list: immutable.Seq[(Char, Char)] = f zip slide
//        list.foreach{
//          g =>
//            if(!seq.contains(g)){
//              seq += g
//            }
//        }
//        slide = slide.tail
//      }
//  }
}
