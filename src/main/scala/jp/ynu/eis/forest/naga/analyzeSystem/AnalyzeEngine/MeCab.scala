package jp.ynu.eis.forest.naga.analyzeSystem.AnalyzeEngine
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.ResultOfAnalyzeEngine._
import jp.ynu.eis.forest.naga.analyzeSystem.dialog.DialogManager
import org.aiwolf.common.data.Talk
import us.feliscat.text.{StringOption, StringSome}
import us.feliscat.text.analyzer.mor.mecab.IpadicMecab

import scala.collection.mutable

object MeCab {
  def setResult(talk: Talk, resm: ResultOfMeCab): Unit ={
    val strsome: StringSome = StringSome(talk.getText)
    val res = IpadicMecab.analyze(strsome)
    res.foreach{
      f =>
        resm.wordData += f.split("\t").head
        resm.wordimData += f.split("\t").last
    }
  }

}
