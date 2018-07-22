package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent

import scala.collection.{immutable, mutable}
import scala.io.Source

object OpponentDetective {

  val seq = mutable.MutableList.empty[(Char, Char)]
  val sourceDir = "resource"
  val KanoSource = Source.fromFile(s"${sourceDir}/kanofact.txt")
  val KldicSource = Source.fromFile(s"${sourceDir}/keldicfact.txt")

  val kanoLines = KanoSource.getLines.toList
  val kanoList = mutable.MutableList.empty[Agent]

  def KanoDetective(dm: DialogManager): Unit ={
    val tlList = dm.taList.talkList.takeRight(4)

    kanoLines.foreach{
      f =>
        tlList.foreach{
          tl =>
            if(f == tl.getText){
              kanoList += tl.getAgent
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
