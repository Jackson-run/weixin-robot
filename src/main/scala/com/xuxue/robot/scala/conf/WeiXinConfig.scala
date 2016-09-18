package com.xuxue.robot.scala.conf

import java.io.{File, FileInputStream}
import java.util.Properties

/**
  * Created by liuwei on 2016/9/14.
  */
class WeiXinConfig(val appId: String, val appSecret: String,
		   val token: String, val encodingAesKey: String) {
  override def toString = s"WeiXinConfig($appId, $appSecret, $token, $encodingAesKey)"
}

object WeiXinConfig {

  def apply(): WeiXinConfig = {
     new WeiXinConfig("wx37a36c20d5407a7b",
      "34b7a07be575f3fe1e487953abd4c4e5","xuxuerobot",
      "BmKRiL2FVDuQnS8vfWeOj5TegEHyu1VOcNFFddBmoGa")
  }

  def main(args: Array[String]): Unit = {
    
    val confPath = "conf/weixin.conf"
    
    val conf = WeiXinConfig()
    
    println(conf)
  }

}
