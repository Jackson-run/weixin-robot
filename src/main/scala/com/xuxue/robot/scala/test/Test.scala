package com.xuxue.robot.scala.test

/**
  * Created by liuwei on 2016/9/11.
  */
class Test {

}

object Test{

  def main(args: Array[String]): Unit = {


    val xml=
      <xml>
    <ToUserName>gh_e2032e32b422</ToUserName>
        <FromUserName>oGGwgxDRpVnCCqPJD-KFWThBcqBA</FromUserName>
        <CreateTime>1473594851</CreateTime>
        <MsgType>text</MsgType>
        <Content>海</Content>
        <MsgId>6329041693018996674</MsgId>
      </xml>
    //println(xml)

    println((xml \\ "MsgId").text)

  }

}
