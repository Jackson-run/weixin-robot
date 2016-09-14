package com.xuxue.robot.weixin.test.scala

import com.xuxue.robot.scala.tuling.{Question, TuLingRobot}

/**
  * Created by liuwei on 2016/9/13.
  */
class TestTuLingRobot {

  @org.junit.Test
  def testPost(){
    val question=new Question("6606a03f6fbc4ee282a4bc735cd3c1db","hahahaha","hahahaha","hahahaha")
      val robot=new TuLingRobot();
    println(question.toString)
   println(robot.getAnswler(question))
  }

}
