package jp.ynu.eis.forest.naga.analyzeSystem.UtteranceAnalysis

import java.util

import jp.ynu.eis.forest.naga.OpponentMetaData._
import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.{CaboCha, MeCab, OpponentDetective}
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import jp.ynu.eis.forest.naga.result.UtteranceResult
import jp.ynu.eis.forest.naga.result.minds._
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo

import scala.collection.mutable

trait UtteranceAnalyzer {
  val dm: DialogManager
  val gameInfo: GameInfo = dm.gameInfoList.last
  val recentTalkList: mutable.MutableList[Talk] = dm.taList.talkList.filter(_.getTurn == dm.taList.talkList.last.getTurn)
  var mind: SituationMind = RandomTalk

  if (dm.taList.talkList.nonEmpty) {
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
    CaboCha.getResult(dm)
    MeCab.getResult(dm)
    OpponentDetective.collectEnemyName(dm)

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
      OpponentDetective.setEnemyname(dm)
      //var sentence = ""
      if (dm.taList.anaList.resod.kano.nonEmpty && dm.callkano.get != gameInfo.getAgent && !dm.nameDetectList.contains(Kanolab)) {
        mind = OpponentJudge(dm.callkano.get, Kanolab)
        dm.nameDetectList += Kanolab
      }
      else if (dm.taList.anaList.resod.keldic.nonEmpty && dm.callkeldic.get != gameInfo.getAgent && !dm.nameDetectList.contains(Keldic)) {
        mind = OpponentJudge(dm.callkeldic.get, Keldic)
        dm.nameDetectList += Keldic
      }
      else if (dm.taList.anaList.resod.mcre.nonEmpty && dm.callmcre.get != gameInfo.getAgent && !dm.nameDetectList.contains(Mcre)) {
        mind = OpponentJudge(dm.callkeldic.get, Mcre)
        dm.nameDetectList += Mcre
      }
      else if (dm.taList.anaList.resod.wordWolf.nonEmpty && dm.callwordWlof.get != gameInfo.getAgent && !dm.nameDetectList.contains(Wordwolf)) {
        mind = OpponentJudge(dm.callkeldic.get, Wordwolf)
        dm.nameDetectList += Wordwolf
      }
      else if (dm.taList.anaList.resod.udon.nonEmpty && dm.taList.anaList.resod.udon.get != gameInfo.getAgent && !dm.nameDetectList.contains(Udon)) {
        mind = OpponentJudge(dm.callkeldic.get, Udon)
        dm.nameDetectList += Udon
      }

      if (dm.taList.anaList.resod.kano.nonEmpty && dm.seerList.contains(dm.taList.anaList.resod.kano.get)) {

        mind = KanoMeta(dm.callkano.get)

      }

    }

  }
}
