package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent

import scala.collection.{immutable, mutable}
import scala.io.Source

object OpponentDetective {

  val seq: mutable.MutableList[(Char, Char)] = mutable.MutableList.empty[(Char, Char)]
  val sourceDir = "resource/fact"

  val KanoSource = Source.fromFile(s"${sourceDir}/kanofact.txt")
  val KeldicSource = Source.fromFile(s"${sourceDir}/keldicfact.txt")
  val McreSource = Source.fromFile(s"${sourceDir}/mcrefact.txt")
  val WordWolfSource = Source.fromFile(s"${sourceDir}/wordwolffact.txt")

  val kanoLines = KanoSource.getLines.toList
  val keldicLines = KeldicSource.getLines.toList
  val mcreLines = McreSource.getLines.toList
  val wordwolfLines = WordWolfSource.getLines.toList

  def collectEnemyName(dm: DialogManager): Unit = {
    val tlList = dm.taList.talkList.takeRight(4)
    val odList = dm.taList.anaList.resod

    tlList.foreach {
      tl =>
        val tl00: String = tl.getText.replaceAll("""\[[0-9][0-9]\]""", "[00]")
        if (tl.isOver || tl.isSkip) {
          //no action

        }
        if (kanoLines.contains(tl00)) {
          dm.taList.anaList.resod.kanoList += tl.getAgent

        }
        if (keldicLines.contains(tl00)) {
          dm.taList.anaList.resod.keldicList += tl.getAgent

        }
        if (mcreLines.contains(tl00)) {
          dm.taList.anaList.resod.mcreList += tl.getAgent

        }
        if (wordwolfLines.contains(tl00)) {
          dm.taList.anaList.resod.wordWolfList += tl.getAgent

        }
        else {
          dm.taList.anaList.resod.indigoList += tl.getAgent
        }
//        println(dm.taList.anaList.resod.kanoList)
    }
  }

  def setEnemyname(dm: DialogManager): Unit = {
    var Max = 0
    var tag: Agent = dm.gameInfoList.last.getAgent
    var agentList = dm.agentListChange(dm.gameInfoList.last.getAliveAgentList)

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.kanoList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.kanoList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.kano = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.keldicList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.keldicList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.keldic = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.mcreList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.mcreList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.mcre = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.mcreList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.mcreList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.mcre = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.wordWolfList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.wordWolfList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.wordWolf = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0
    dm.taList.anaList.resod.indigo = Option(agentList.last)

  }


  //      val kano: Agent = dm.taList.anaList.resod.kanoList.maxBy(ag => dm.taList.anaList.resod.kanoList.count(kanoag => ag == kanoag))
  //      dm.taList.anaList.resod.kano = Option(kano)
  //      agentList = agentList.filter(ag => ag != kano)
  //
  //      dm.taList.anaList.resod.keldicList = dm.taList.anaList.resod.keldicList.filter(f => f != kano)
  //
  //      val keldic: Agent = dm.taList.anaList.resod.keldicList.maxBy(ag => dm.taList.anaList.resod.keldicList.count(kanoag => ag == kanoag))
  //      dm.taList.anaList.resod.keldic = Option(keldic)
  //
  //      dm.taList.anaList.resod.mcreList = dm.taList.anaList.resod.mcreList.filter(f => f != kano)
  //      dm.taList.anaList.resod.mcreList = dm.taList.anaList.resod.mcreList.filter(f => f != keldic)
  //      dm.taList.anaList.resod.mcreList.maxBy(ag => dm.taList.anaList.resod.mcreList.count(kanoag => ag == kanoag))
  //      val keldic: Agent = dm.taList.anaList.resod.keldicList.maxBy(ag => dm.taList.anaList.resod.keldicList.count(kanoag => ag == kanoag))
  //      dm.taList.anaList.resod.keldic = Option(keldic)


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
