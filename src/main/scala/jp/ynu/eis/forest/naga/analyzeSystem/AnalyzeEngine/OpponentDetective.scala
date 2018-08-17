package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Agent

import scala.collection.{immutable, mutable}
import scala.io.Source

object OpponentDetective {

  val seq: mutable.MutableList[(Char, Char)] = mutable.MutableList.empty[(Char, Char)]
  val sourceDir = "resource"
  val factSourceDir = s"$sourceDir/fact"

  var kanoLines : List[String] = List.empty[String]
  var keldicLines : List[String] = List.empty[String]
  var mcreLines : List[String] = List.empty[String]
  var wordwolfLines : List[String] = List.empty[String]
  var UdonLines : List[String] = List.empty[String]
  var RosenLines : List[String] = List.empty[String]
  var FukuLines : List[String] = List.empty[String]
  var AriesLines : List[String] = List.empty[String]
  var CanisLines : List[String] = List.empty[String]

  var nagaSentenceList: List[String] =  List.empty[String]
  var questionSentenceList: List[String] =  List.empty[String]

  //ファイルからデータを読み込む処理は全てこのメソッド内で行うこと。
  def inputTextResources() : Unit = {
    val KanoSource = Source.fromFile(s"$factSourceDir/kanofact.txt")
    val KeldicSource = Source.fromFile(s"$factSourceDir/keldicfact.txt")
    val McreSource = Source.fromFile(s"$factSourceDir/mcrefact.txt")
    val WordWolfSource = Source.fromFile(s"$factSourceDir/wordwolffact.txt")

    val UdonSource = Source.fromFile(s"$factSourceDir/udonfact.txt")
    val RosenblattSource = Source.fromFile(s"$factSourceDir/rosenblattfact.txt")
    val Fuku6uSource = Source.fromFile(s"$factSourceDir/fuku6ufact.txt")
    val AriesSource = Source.fromFile(s"$factSourceDir/ariesfact.txt")
    val CanisSource = Source.fromFile(s"$factSourceDir/canisLupusfact.txt")

    val nagaSource = Source.fromFile(s"$sourceDir/naga.txt")
    val questionSource = Source.fromFile(s"$sourceDir/question.txt")


    kanoLines = KanoSource.getLines.toList
    keldicLines = KeldicSource.getLines.toList
    mcreLines = McreSource.getLines.toList
    wordwolfLines = WordWolfSource.getLines.toList

    UdonLines = UdonSource.getLines.toList
    RosenLines = RosenblattSource.getLines.toList
    FukuLines = Fuku6uSource.getLines.toList
    AriesLines = AriesSource.getLines.toList
    CanisLines = CanisSource.getLines.toList

    nagaSentenceList = nagaSource.getLines.toList
    questionSentenceList = questionSource.getLines.toList
  }

  def collectEnemyName(dm: DialogManager): Unit = {
    val tlList = dm.taList.talkList.takeRight(4)
    //val odList = dm.taList.anaList.resod

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
        if (UdonLines.contains(tl00)) {
          dm.taList.anaList.resod.udonList += tl.getAgent

        }
        if (RosenLines.contains(tl00)) {
          dm.taList.anaList.resod.rosenList += tl.getAgent

        }
        if (FukuLines.contains(tl00)) {
          dm.taList.anaList.resod.fukuList += tl.getAgent

        }
        if (AriesLines.contains(tl00)) {
          dm.taList.anaList.resod.ariesList += tl.getAgent

        }
        if (CanisLines.contains(tl00)) {
          dm.taList.anaList.resod.cainsList += tl.getAgent

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

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.udonList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.udonList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.udon = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.rosenList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.rosenList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.rosen = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.fukuList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.fukuList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.fuku = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.ariesList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.ariesList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.aries = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.taList.anaList.resod.cainsList.count(nameag => nameag == ag)) {
          Max = dm.taList.anaList.resod.cainsList.count(nameag => nameag == ag)
          dm.taList.anaList.resod.cains = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    dm.taList.anaList.resod.indigo = Option(agentList.last)

  }




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
