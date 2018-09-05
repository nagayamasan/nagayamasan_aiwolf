package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import jp.ynu.eis.forest.naga.OpponentMetaData._
import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab, OpponentDetective}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, NeoTalk}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds._
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

trait UtteranceAnalyzer {
  val dm: DialogManager
  val gameInfo: GameInfo = dm.gameInfoList.last
  val recentTalkList: mutable.MutableList[NeoTalk] = dm.neoTalkList.filter(_.getTurn == dm.neoTalkList.last.getTurn)
  var mind: SituationMind = RandomTalk

  if (dm.neoTalkList.nonEmpty) {
    spellAnalyze()
    seerDetective()
  }

  def analyze(): Unit


  def getResult: UtteranceResult = {
    val myname = dm.gameInfoList.last.getAgent.toString
    val question = dm.questionRegex
    val isQA = recentTalkList.exists (f => question.findFirstIn(f.getText).nonEmpty && f.getText.contains(">>" + myname))

    if (!isQA && mind == RandomTalk) {
      gomi
    }
    UtteranceResult(dm, isQA, mind)

  }

  def spellAnalyze(): Unit = {
    recentTalkList.foreach{
      neotalk=>
        neotalk.setCaboChaData
        neotalk.setMeCabData
        OpponentDetective.collectEnemyName(neotalk.talk, dm.resultOfOpp)
    }
  }


  def seerDetective(): Unit = {
    //会話が進む毎に占い師の判定をする

    val iam = "私".r
    val seer = "占い".r
    val kekka = "結果".r
    if (recentTalkList.nonEmpty) {
      recentTalkList.foreach {
        f =>
          if (seer.findFirstIn(f.getText).nonEmpty) {
            dm.seerList += f.getAgent

          }
          else if (kekka.findFirstIn(f.getText).nonEmpty) {
            dm.seerList += f.getAgent
          }
      }
    }
  }

  private def gomi: Unit = {
    import scala.util.control.Breaks
    //val break = new Breaks
    if (dm.gameInfoList.last.getDay == 0) {
      if (dm.getTurn == 1) {
        mind = Greeting(0)
        //break.break()

        // "お手柔らかにお願いします。"
      } else {
        mind = TalkOver
        //break.break()

        // Talk.OVER
      }
    }
    else if (gameInfo.getDay == 1 && dm.getTurn == 1) {
      mind = SeerComeon
      //break.break()
    }
    else if (gameInfo.getDay == 2 && dm.getTurn == 1) {
      mind = Greeting(gameInfo.getDay)
    }
    else if (dm.talkListChange(gameInfo.getTalkList).last.getTurn >= dm.VOTE_DECIDED_TURN) {
      mind = TalkOver
      // break.break()
    }
    else if (gameInfo.getDay == 1 && dm.getTurn == dm.TURN_AGENT_ATERU_NUMBER) {
      OpponentDetective.setEnemyname(dm.resultOfOpp, dm.agentList)
      //var sentence = ""
      if (dm.resultOfOpp.kano.nonEmpty &&
        dm.getKano.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Kanolab)){
        mind = OpponentJudge(dm.getKano.get, Kanolab)
        dm.nameDetectList += Kanolab
      }
      else if (dm.resultOfOpp.keldic.nonEmpty &&
        dm.getKeldic.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Keldic)) {
        mind = OpponentJudge(dm.getKeldic.get, Keldic)
        dm.nameDetectList += Keldic
      }
      else if (dm.resultOfOpp.mcre.nonEmpty &&
        dm.getMcre.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Mcre)) {
        mind = OpponentJudge(dm.getMcre.get, Mcre)
        dm.nameDetectList += Mcre
      }
      else if (dm.resultOfOpp.wordWolf.nonEmpty &&
        dm.getWordWolf.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Wordwolf)) {
        mind = OpponentJudge(dm.getWordWolf.get, Wordwolf)
        dm.nameDetectList += Wordwolf
      }
      else if (dm.resultOfOpp.udon.nonEmpty &&
        dm.getUdon.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Udon)) {
        mind = OpponentJudge(dm.getUdon.get, Udon)
        dm.nameDetectList += Udon
      }
      else if (dm.resultOfOpp.rosen.nonEmpty &&
        dm.getRosen.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Rosenblatt)) {
        mind = OpponentJudge(dm.getRosen.get, Rosenblatt)
        dm.nameDetectList += Rosenblatt
      }
      else if (dm.resultOfOpp.fuku.nonEmpty &&
        dm.getFuku.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Fuku6u)) {
        mind = OpponentJudge(dm.getFuku.get, Fuku6u)
        dm.nameDetectList += Fuku6u
      }
      else if (dm.resultOfOpp.aries.nonEmpty &&
        dm.getAries.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Aries)) {
        mind = OpponentJudge(dm.getAries.get, Aries)
        dm.nameDetectList += Aries
      }
      else if (dm.resultOfOpp.cains.nonEmpty &&
        dm.getCains.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(CainsLupus)) {
        mind = OpponentJudge(dm.getCains.get, CainsLupus)
        dm.nameDetectList += CainsLupus
      }
      else if (dm.resultOfOpp.indigo.nonEmpty &&
        dm.getIndigo.get != gameInfo.getAgent &&
        !dm.nameDetectList.contains(Indigo)) {
        mind = OpponentJudge(dm.getIndigo.get, Indigo)
        dm.nameDetectList += Indigo
      }

      if (dm.resultOfOpp.kano.nonEmpty &&
        dm.seerList.contains(dm.resultOfOpp.kano.get)) {
        mind = KanoMeta(dm.getKano.get)
      }

    }

  }
}
