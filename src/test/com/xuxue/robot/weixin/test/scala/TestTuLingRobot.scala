package com.xuxue.robot.weixin.test.scala

import com.xuxue.robot.scala.tuling.{Question, TuLingRobot}

import scala.xml.XML

/**
  * Created by liuwei on 2016/9/13.
  */
object TestTuLingRobot {
  
  def main(args: Array[String]): Unit = {
    
    val x=XML.loadString("<xml><ToUserName><![CDATA[gh_e2032e32b422]]></ToUserName>\n<FromUserName><![CDATA[oGGwgxDRpVnCCqPJD-KFWThBcqBA]]></FromUserName>\n<CreateTime>1474092502</CreateTime>\n<MsgType><![CDATA[text]]></MsgType>\n<Content><![CDATA[真是]]></Content>\n<MsgId>6331179087788873987</MsgId>\n</xml>")
    
    println(x)
    
    
  }
  

}
