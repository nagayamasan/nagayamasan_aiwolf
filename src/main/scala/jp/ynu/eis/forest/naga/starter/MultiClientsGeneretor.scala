package jp.ynu.eis.forest.naga.starter


object MultiClientsGeneretor {

  def main(args: Array[String]): Unit = {
    NagaPlayer.clientStart(5)
  }
}
