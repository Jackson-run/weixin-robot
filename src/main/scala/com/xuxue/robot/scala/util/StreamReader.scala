package com.xuxue.robot.scala.util

import java.io._


import scala.annotation.tailrec


/**
  * Created by liuwei on 2016/9/11.
  */
class StreamReader {



}

object StreamReader{

    def toBytes(in:InputStream):Array[Byte]={
      val bufferBytes=new ByteArrayOutputStream();
      @tailrec
      def read(in:InputStream,bytes:Array[Byte]):Unit={
        val a=in.read(bytes,0,bytes.length);
        if(a != -1) {
          bufferBytes.write(bytes, 0, a);
          read(in,bytes);
        }
      }
      read(in,new Array[Byte](128));
      bufferBytes.toByteArray
    }

  def toUTF8String(in:InputStream):String={
    new String(toBytes(in))
  }



}
