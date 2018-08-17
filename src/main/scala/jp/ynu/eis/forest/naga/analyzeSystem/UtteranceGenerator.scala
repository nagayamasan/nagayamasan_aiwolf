package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.OpponentMetaData._
import jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine.OpponentDetective
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import jp.ynu.eis.forest.naga.result.minds._
import org.aiwolf.common.data.{Agent, Role, Species, Talk}

import scala.util.Random

case class UtteranceGenerator(mind: SituationMind, dm: DialogManager) {



  def getResult: String= {

    mind match {
      case RandomTalk => randomTalk
      case RoleCo(role) =>
        role match{
          case Role.SEER => "私は占い師です"
          case Role.POSSESSED => "あ、どーも僕が狂人です。"
          case Role.WEREWOLF => "ワオーン"
          case Role.VILLAGER => "村人です。"
          case _ => randomTalk
        }
      case DivineResult(ag,sp) =>
        sp match {
          case Species.HUMAN =>"占いの結果" + ag + "は人間でした。"
          case Species.WEREWOLF => "占いの結果" + ag + "は人狼でした。"
        }
      case VoteCo(ag) => ag + "に投票するわ。"
      case VoteCantChoose => "投票先絞れんわ。"
      case PossessedDetect => "狂人把握した。"
      case OpponentJudge(ag, op) =>s"${ag}はひょっとすると${op}さんですか。"
      case Greeting(0) => "お手柔らかにお願いします。"
      case SeerComeon => "おはよう。占い師いる？"
      case TalkOver => Talk.OVER
      case TalkSkip => Talk.SKIP
      case KanoMeta(ag) => s"${ag}は真か狼だよ"
      case _ => randomTalk
    }



  }

  def randomTalk : String ={
    if(Random.nextBoolean){

      val sentenceList = OpponentDetective.nagaSentenceList
      sentenceList(Random.nextInt(sentenceList.size))

    }
    else{
      //あらかじめシャッフルしておく
      val aliveAgentList = Random.shuffle(dm.agentListChange(dm.gameInfoList.last.getAliveAgentList).filter{
        f => f != dm.gameInfoList.last.getAgent
      })
      val sentenceList = OpponentDetective.questionSentenceList
      //aliveAgentListはシャッフル済み
      val enemyName: Agent = aliveAgentList.head
      //Agent[00]を実際のものに置換(目的語のAgentは別の生きている人に)
      val returnMessage = Random.shuffle(sentenceList).head.replaceFirst("""Agent\[00\]""",aliveAgentList.last.toString)

      s">>$enemyName $enemyName$returnMessage"
    }
  }
}
