package jp.ynu.eis.forest.naga.result

import java.util

import jp.ynu.eis.forest.naga.analyzeSystem.dialog.{DialogManager, TalkAnalyzeList}
import org.aiwolf.common.data.Talk
import org.aiwolf.common.net.GameInfo
import us.feliscat.text.StringOption
import us.feliscat.text.analyzer.mor.mecab.IpadicMecab

import scala.collection.mutable

case class UtteranceResult(dm: DialogManager, needQA: Boolean, response: String) {
  val reCentTalkList = dm.taList.talkList.takeRight(4)
  val question = "\\?".r
  val questionList = mutable.MutableList.empty[Talk]
  reCentTalkList.foreach{
     text =>
       if(question.findFirstIn(text.getText) != None){
         questionList += text
       }

   }

}
