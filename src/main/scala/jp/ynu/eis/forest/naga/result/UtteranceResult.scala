package jp.ynu.eis.forest.naga.result

import java.util

import org.aiwolf.common.data.Talk
import us.feliscat.text.StringOption
import us.feliscat.text.analyzer.mor.mecab.IpadicMecab

import scala.collection.mutable

case class UtteranceResult(talkList: mutable.MutableList[Talk], needQA: Boolean, response: String) {


}
