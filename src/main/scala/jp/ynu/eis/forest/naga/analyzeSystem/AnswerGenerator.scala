package jp.ynu.eis.forest.naga.analyzeSystem

import jp.ynu.eis.forest.naga.result.QuestionResult

case class AnswerGenerator(question: QuestionResult){
  def getResresult: String = {
    var resresult = ""
    if(question.questionClass("vote")){
      resresult = "教えない。"
    }else if(question.questionClass("role")){
      resresult = "ひみつ。"
    }else if(question.questionClass("reason")){
      resresult = "なんとなくだよ。"
    }
    resresult
  }
}
