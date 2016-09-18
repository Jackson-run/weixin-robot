package com.xuxue.robot.scala.tuling

import java.io.ByteArrayInputStream

import com.xuxue.robot.scala.conf.TuLingConfig
import org.apache.http.impl.client.HttpClients
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.BasicHttpEntity
import com.xuxue.robot.scala.util.Loan.use
import com.xuxue.robot.scala.util.StreamReader
import org.apache.http.client.config.RequestConfig
import com.xuxue.robot.scala.util.Gs
/**
  * Created by liuwei on 2016/9/11.
  */
class TuLingRobot {

  val client=HttpClients.custom()
                            .setMaxConnTotal(200).build()

  def getAnswer(question:Question,toUser:String,fromUSer:String):String={
      val serverResponse=postTuLingServer(question);
      matchAnswer(serverResponse,toUser,fromUSer)
  }


  def postTuLingServer(question: Question):String={
    val json=question.toString
    val post=new HttpPost(TuLingRobot.url)
    val entry=new BasicHttpEntity
    entry.setContent(new ByteArrayInputStream(json.getBytes()))

    post.setConfig(RequestConfig.custom().setConnectTimeout(5000)
      .setSocketTimeout(5000)
      .setConnectionRequestTimeout(5000)
      .build())
    post.setEntity(entry)
    use(client.execute(post)){
      res=>
        val json=StreamReader.toUTF8String(res.getEntity.getContent);
        json
    }
  }
  

  /**
    *
    * @param json 图灵机器人返回的json
    * @return 需要返回的xml
    */
  def matchAnswer(json:String,toUser:String,fromUser:String):String={
    println(json)
    val answer=Gs.G.fromJson(json,classOf[TextAnswer])
    val xml=answer.code match {
      case 100000=>{
        val xml=
          <xml>
        <ToUserName>{toUser}</ToUserName>
          <FromUserName>{fromUser}</FromUserName>
          <CreateTime>{System.currentTimeMillis()/1000}</CreateTime>
          <MsgType>text</MsgType>
          <Content>{answer.text}</Content>
        </xml>
        xml.toString()
      }
      case 200000=>{
        val xml=
          <xml>
            <ToUserName>{toUser}</ToUserName>
            <FromUserName>{fromUser}</FromUserName>
            <CreateTime>{System.currentTimeMillis()/1000}</CreateTime>
            <MsgType>text</MsgType>
            <Content>{answer.text}</Content>
          </xml>
        xml.toString()
      }
      case 302000=>{
        val xml=
          <xml>
            <ToUserName>{toUser}</ToUserName>
            <FromUserName>{fromUser}</FromUserName>
            <CreateTime>{System.currentTimeMillis()/1000}</CreateTime>
            <MsgType>text</MsgType>
            <Content>{answer.text}</Content>
          </xml>
        xml.toString()
      }
      case 308000=>{
        val xml=
          <xml>
            <ToUserName>{toUser}</ToUserName>
            <FromUserName>{fromUser}</FromUserName>
            <CreateTime>{System.currentTimeMillis()/1000}</CreateTime>
            <MsgType>text</MsgType>
            <Content>{answer.text}</Content>
          </xml>
        xml.toString()
      }
        
    }
   xml
  }

}


case class Question(key:String,info:String,loc:String,userID:String){
  override def toString=Gs.G.toJson(this)
}

abstract class Answer()

case class TextAnswer(val code:Int,val text:String) extends Answer;

case class URLAnswer(val code:Int,text:String,url:Array[String]) extends Answer

case class News(article:String,source:Int,icon:String,detailurl:String)

case class NewsAnswer(val code:Int,text:String,list:Array[News])extends Answer

object TuLingRobot{
  
  
  val url="http://www.tuling123.com/openapi/api"
  def main(args: Array[String]): Unit = {
    val conf=TuLingConfig()
    val question=new Question(conf.key,"你好啊",null,null)
    val robot=new TuLingRobot
    val xml=robot.getAnswer(question,"我","你")
  }
}
