package com.xuxue.robot.scala.util

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

/**
  *
  *
  *
  * @ time 2016-9-11
  *
  * @author xuxue
  *
  */
object Loan {


  /*def use[T<:{def close();}](resource:T)(func:T=>Unit)={
    try{
      func(resource)
    }finally {
      resource.close()
    }
  }*/

  def use[T<:{def close();},R](resource:T)(func:T=>R)={
    try{
      func(resource)
    }finally {
      resource.close()
    }
  }

}
