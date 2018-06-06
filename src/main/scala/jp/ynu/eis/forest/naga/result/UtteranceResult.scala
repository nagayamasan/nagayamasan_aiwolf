package jp.ynu.eis.forest.naga.result

import java.util
import org.aiwolf.common.data.Talk
import us.feliscat.text.StringOption
import us.feliscat.text.analyzer.mor.mecab.IpadicMecab

case class UtteranceResult(talkList: util.List[Talk], needQA: Boolean, response: String) {


}
