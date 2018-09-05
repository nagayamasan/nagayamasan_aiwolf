package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine.ResultOfOppDet
import org.aiwolf.common.data.{Agent, Talk}
import org.aiwolf.common.net.GameInfo

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

  def collectEnemyName(talk: Talk, resultofopp: ResultOfOppDet): Unit = {
    //特定できるトークをその候補リストに入れる

    val convertTalk: String = talk.getText.replaceAll("""\[[0-9][0-9]\]""", "[00]")
    if (talk.isOver || talk.isSkip) {
      //no action
    }
    if (kanoLines.contains(convertTalk)) {
      resultofopp.kanoList += talk.getAgent
    }
    if (keldicLines.contains(convertTalk)) {
      resultofopp.keldicList += talk.getAgent
    }
    if (mcreLines.contains(convertTalk)) {
      resultofopp.mcreList += talk.getAgent
    }
    if (wordwolfLines.contains(convertTalk)) {
      resultofopp.wordWolfList += talk.getAgent
    }
    if (UdonLines.contains(convertTalk)) {
      resultofopp.udonList += talk.getAgent
    }
    if (RosenLines.contains(convertTalk)) {
      resultofopp.rosenList += talk.getAgent
    }
    if (FukuLines.contains(convertTalk)) {
      resultofopp.fukuList += talk.getAgent
    }
    if (AriesLines.contains(convertTalk)) {
      resultofopp.ariesList += talk.getAgent
    }
    if (CanisLines.contains(convertTalk)) {
      resultofopp.cainsList += talk.getAgent
    }
    else {
      resultofopp.indigoList += talk.getAgent
    }
  }

  def setEnemyname(resultofoppdet: ResultOfOppDet, agentlist: mutable.MutableList[Agent]): Unit = {
    var kanoMax = 0
    var keldicMax = 0
    var mcreMax = 0
    var wordWolfMax = 0
    var udonMax = 0
    var rosenMax = 0
    var fukuMax = 0
    var ariesMax = 0
    var cainsMax = 0

    agentlist.foreach{
      agent =>
        if(kanoMax <= resultofoppdet.kanoList.count(_ == agent)){
          kanoMax = resultofoppdet.kanoList.count(_ == agent)
          resultofoppdet.kano = Option(agent)
        }
        if(keldicMax <= resultofoppdet.keldicList.count(_ == agent)) {
          keldicMax = resultofoppdet.keldicList.count(_ == agent)
          resultofoppdet.keldic = Option(agent)
        }
        if(mcreMax <= resultofoppdet.mcreList.count(_ == agent)) {
          mcreMax = resultofoppdet.mcreList.count(_ == agent)
          resultofoppdet.mcre = Option(agent)
        }
        if(wordWolfMax <= resultofoppdet.wordWolfList.count(_ == agent)) {
          wordWolfMax = resultofoppdet.wordWolfList.count(_ == agent)
          resultofoppdet.wordWolf = Option(agent)
        }
        if(udonMax <= resultofoppdet.udonList.count(_ == agent)) {
          udonMax = resultofoppdet.udonList.count(_ == agent)
          resultofoppdet.udon = Option(agent)
        }
        if(rosenMax <= resultofoppdet.rosenList.count(_ == agent)) {
          rosenMax = resultofoppdet.rosenList.count(_ == agent)
          resultofoppdet.rosen = Option(agent)
        }
        if(fukuMax <= resultofoppdet.fukuList.count(_ == agent)) {
          fukuMax = resultofoppdet.fukuList.count(_ == agent)
          resultofoppdet.fuku = Option(agent)
        }
        if(ariesMax <= resultofoppdet.ariesList.count(_ == agent)) {
          ariesMax = resultofoppdet.ariesList.count(_ == agent)
          resultofoppdet.aries = Option(agent)
        }
        if(cainsMax <= resultofoppdet.cainsList.count(_ == agent)) {
          cainsMax = resultofoppdet.cainsList.count(_ == agent)
          resultofoppdet.cains = Option(agent)
        }
    }

    //var tag: Agent = dm.gameInfoList.last.getAgent
    //var agentList = dm.agentListChange(dm.gameInfoList.last.getAliveAgentList)

    /*
    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.kanoList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.kanoList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.kano = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.keldicList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.keldicList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.keldic = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.mcreList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.mcreList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.mcre = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.mcreList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.mcreList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.mcre = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.wordWolfList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.wordWolfList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.wordWolf = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.udonList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.udonList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.udon = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.rosenList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.rosenList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.rosen = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.fukuList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.fukuList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.fuku = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.ariesList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.ariesList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.aries = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    agentList.foreach {
      ag =>
        if (Max < dm.neoTalkList.allAnalyzeList.resultofopp.cainsList.count(nameag => nameag == ag)) {
          Max = dm.neoTalkList.allAnalyzeList.resultofopp.cainsList.count(nameag => nameag == ag)
          dm.neoTalkList.allAnalyzeList.resultofopp.cains = Option(ag)
          tag = ag
        }
    }
    agentList = agentList.filter(f => f != tag)
    Max = 0

    dm.neoTalkList.allAnalyzeList.resultofopp.indigo = Option(agentList.last)
*/
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
