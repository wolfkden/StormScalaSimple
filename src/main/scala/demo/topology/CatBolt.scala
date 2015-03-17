package demo.topology

import java.util
import java.util.{Map => JMap}

import backtype.storm.task.{OutputCollector, TopologyContext}
import backtype.storm.topology.{BasicOutputCollector, OutputFieldsDeclarer}
import backtype.storm.topology.base.BaseRichBolt
import backtype.storm.tuple.{Values, Fields, Tuple}
//import com.google.gson.Gson

/**
 * Created by wolf on 3/16/15.
 */
class CatBolt //(val streamToFields: collection.Map[String, List[String]])
   extends BaseRichBolt {

  private var counts:Map[String,Int] = Map[String,Int]()
  private var _outputCollector: OutputCollector = null
  private var _context:TopologyContext = null
  private var _conf:JMap[_,_] = null

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext,
                       collector: OutputCollector): Unit = {
    _context = context
    _conf = stormConf
    _outputCollector = collector

  }

  override def execute(tuple: Tuple): Unit = {
    val message:String = tuple.getStringByField("city")
    counts += (if(counts.contains(message)) (message->(counts(message)+1)) else (message,1))
    val om:String = "Hello From: " + message + "!" + " Count: " + counts(message)
    _outputCollector.emit(new Values(om))

  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {

    declarer.declare(new Fields("HelloFromCity"))
  }

  def printCounts(): Unit = {
    counts.foreach(p => println("City: " + p._1 + " Value: " + p._2))
  }
}
