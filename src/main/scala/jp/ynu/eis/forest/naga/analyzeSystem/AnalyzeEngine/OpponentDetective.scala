package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent

import scala.collection.{immutable, mutable}
import scala.io.Source

object OpponentDetective{

  val seq: mutable.MutableList[(Char, Char)] = mutable.MutableList.empty[(Char, Char)]
  val sourceDir = "resource/fact"

  val KanoSource = Source.fromFile(s"${sourceDir}/kanofact.txt")
  val KeldicSource = Source.fromFile(s"${sourceDir}/keldicfact.txt")
  val McreSource = Source.fromFile(s"${sourceDir}/mcrefact.txt")
  val WordWolfSource = Source.fromFile(s"${sourceDir}/wordwolffact.txt")

  val kanoLines = KanoSource.getLines
  val keldicLines = KeldicSource.getLines
  val mcreLines = McreSource.getLines
  val wordwolfLines = WordWolfSource.getLines

  def collectEnemyName(dm: DialogManager): Unit ={
    val tlList = dm.taList.talkList.takeRight(4)
    val odList = dm.taList.anaList.resod

    tlList.foreach{
      tl =>
        tl.getText.replaceAll("""\[[0-9][0-9]\]""" ,"[00]")

        if(tl.isOver || tl.isSkip){
          //no action

        }
        if(kanoLines.contains(tl.getText)) {
          dm.taList.anaList.resod.kanoList += tl.getAgent

        }
        if(keldicLines.contains(tl.getText)) {
            dm.taList.anaList.resod.keldicList += tl.getAgent

        }
        if(mcreLines.contains(tl.getText)) {
          dm.taList.anaList.resod.mcreList += tl.getAgent

        }
        if(wordwolfLines.contains(tl.getText)) {
          dm.taList.anaList.resod.wordwolfList += tl.getAgent

        }
        else{
          dm.taList.anaList.resod.indigoList += tl.getAgent
        }
    }
    def setEnemyname(dm: DialogManager): Unit ={
      var kanoMax: Int = 0
      var keldicMax: Int = 0
      var mcreMax: Int = 0
      var wordwolfMax: Int = 0
      var indigoMax: Int = 0

      tlList.foreach {
        tl =>
          if(kanoMax < dm.taList.anaList.resod.kanoList.count(ag => ag == tl.getAgent)) {
            kanoMax = dm.taList.anaList.resod.kanoList.count(ag => ag == tl.getAgent)
            dm.taList.anaList.resod.kano = Option(tl.getAgent)

          }
          if(keldicMax < dm.taList.anaList.resod.keldicList.count(ag => ag == tl.getAgent)) {
            keldicMax = dm.taList.anaList.resod.keldicList.count(ag => ag == tl.getAgent)
            if(dm.taList.anaList.resod.kano != Option(tl.getAgent)){
              dm.taList.anaList.resod.keldic = Option(tl.getAgent)
            }
          }
          if(mcreMax < dm.taList.anaList.resod.mcreList.count(ag => ag == tl.getAgent)) {
            mcreMax = dm.taList.anaList.resod.mcreList.count(ag => ag == tl.getAgent)
            dm.taList.anaList.resod.mcre = Option(tl.getAgent)

          }
          if(wordwolfMax < dm.taList.anaList.resod.wordwolfList.count(ag => ag == tl.getAgent)) {
            mcreMax = dm.taList.anaList.resod.wordwolfList.count(ag => ag == tl.getAgent)
            dm.taList.anaList.resod.wordWolf = Option(tl.getAgent)

          }
          else{
            indigoMax = dm.taList.anaList.resod.indigoList.count(ag => ag == tl.getAgent)
            dm.taList.anaList.resod.indigo = Option(tl.getAgent)

          }

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
