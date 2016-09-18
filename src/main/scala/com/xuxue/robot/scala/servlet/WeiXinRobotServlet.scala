package com.xuxue.robot.scala.servlet

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.xuxue.robot.scala.conf.TuLingConfig
import com.xuxue.robot.scala.tuling.{Question, TuLingRobot}
import com.xuxue.robot.scala.util.StreamReader

import scala.util.{Failure, Success, Try}
import scala.xml.{Node, XML}

/**
  * Created by liuwei on 2016/9/11.
  */
class WeiXinRobotServlet extends HttpServlet {


  val robot = new TuLingRobot
  
  val tulingConf=TuLingConfig()

  override def doGet(req: HttpServletRequest,
                     resp: HttpServletResponse): Unit = {
    val out = resp.getWriter
    val option = Try {
      val sig = req.getParameter("signature")
      val timestamp = req.getParameter("timestamp")
      val nonce = req.getParameter("nonce")
      val echoS = req.getParameter("echostr")
      println(echoS)
      echoS
    }
    option match {
      case Success(v) => {
        out.println(v)
        println("write " + v);
      };
      case Failure(e) => println("failed")
    }
	 out.flush()
    out.close()
  }

  override def doPost(request: HttpServletRequest,
                      response: HttpServletResponse): Unit = {
    println("post")
    val stream = request.getInputStream
    val xml=XML.load(stream);
    stream.close()
    println("load xml"+xml)
    replay(response,xml)
  }


  def replay(response: HttpServletResponse, reciveXml: Node): Unit = {
    response.setContentType("text/html charset=\"utf-8\"");
    response.setCharacterEncoding("utf-8")
    
    println("recive xml is"+reciveXml)
    System.out.println((reciveXml \\ "Content").text)
    
    val question=new Question(tulingConf.key,
      (reciveXml \\ "Content").text,
      null,
      (reciveXml \\ "FromUserName").text)
    println("question is"+question)
    val xml= robot.getAnswer(question,
      (reciveXml \\ "FromUserName").text
      ,(reciveXml \\ "ToUserName").text)
    
    val w = response.getWriter
    
    println(xml.toString())
    w.write(xml.toString())
    w.flush()
    w.close()
  }
}


object WeiXinRobotServlet {
  val TOKEN = "xuxuerobot"
  
  def main(args: Array[String]): Unit = {
  
  
    val robot = new TuLingRobot
    
    val tuLingConfig=TuLingConfig()
    
    val reciveXml=
      <xml><ToUserName>gh_e2032e32b422</ToUserName>
    <FromUserName>oGGwgxDRpVnCCqPJD-KFWThBcqBA</FromUserName>
      <CreateTime>1474093515</CreateTime>
      <MsgType>text</MsgType>
      <Content>你好呀</Content>
      <MsgId>6331183438590744987</MsgId>
    </xml>
    val question=new Question(tuLingConfig.key,
      (reciveXml \\ "Content").text,
      null,
      (reciveXml \\ "FromUserName").text)
    println("question is"+question)
    val xml= robot.getAnswer(question,
      (reciveXml \\ "FromUserName").text
      ,(reciveXml \\ "ToUserName").text)
    
    println(xml)
  
  }
}
