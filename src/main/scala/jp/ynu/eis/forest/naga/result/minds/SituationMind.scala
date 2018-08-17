package jp.ynu.eis.forest.naga.result.minds

import jp.ynu.eis.forest.naga.OpponentMetaData.OpponentData
import org.aiwolf.common.data.{Agent, Role, Species}

sealed trait SituationMind

case object RandomTalk extends SituationMind
final case class RoleCo(role: Role) extends SituationMind
final case class DivineResult(agent : Agent, species : Species) extends SituationMind
final case class VoteCo(agent : Agent) extends SituationMind
case object VoteCantChoose extends SituationMind
case object PossessedDetect extends SituationMind

final case class OpponentJudge(ag : Agent,op :OpponentData) extends SituationMind
final case class Greeting(day : Int) extends SituationMind
case object SeerComeon extends SituationMind
case object TalkOver extends SituationMind
case object TalkSkip extends SituationMind
final case class KanoMeta(ag: Agent) extends SituationMind//kanolabは占い師か狼のときのみCO