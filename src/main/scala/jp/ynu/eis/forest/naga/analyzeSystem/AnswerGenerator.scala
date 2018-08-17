package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result.QuestionResult
import jp.ynu.eis.forest.naga.result.minds.SituationMind

case class AnswerGenerator(question: QuestionResult){
  def getResresult: SituationMind = {
    var resresult = ""
    val name: String = question.getAgentName

    if(question.questionClass("who")){
      resresult = "お前だよ"
    }else if(question.questionClass("vote")){
      resresult = "教えない。"
    }else if(question.questionClass("role")){
      resresult = "ひみつ。"
    }else if(question.questionClass("reason")){
      resresult = "なんとなくだよ。"
    }

    if(resresult != "" && name != ""){

      name + resresult
    }
    else{
      "いやーさっぱり。"
    }
  }
}
