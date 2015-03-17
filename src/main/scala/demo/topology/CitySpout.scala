package demo.topology

import java.util

import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.task.TopologyContext
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichSpout
import backtype.storm.tuple.Fields
import backtype.storm.tuple.Values;
import com.apple.jobjc.NativeObjectLifecycleManager.Nothing
import collection.JavaConverters._
import collection.JavaConversions._
import util.Random
import java.util.Map

/**
 * Created by wolf on 3/16/15.
 */
class CitySpout //(val streamToFields: collection.Map[String, List[String]], val isDistributed: Boolean)
  extends BaseRichSpout {
  private var _outputCollector:SpoutOutputCollector = null
  private var _context:TopologyContext = null
  private var _conf:Map[_,_] = null
  private val rand:Random = new Random
  private var cities: List[String] = List[String]()

  override def open(conf: util.Map[_, _], context: TopologyContext,
                    collector: SpoutOutputCollector): Unit = {
    _outputCollector = collector
    _context = context
    _conf = conf
    cities = List("Dallas", "Los Angeles", "Seattle", "Austin", "Boston", "New York", "Atlanta")
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("city"))
  }
//  streamToFields foreach {
//    case(stream,fields) => declarer.declareStream(stream, new Fields(fields:_*))
//  }

  override def nextTuple(): Unit = {
      _outputCollector.emit(new Values(cities(rand.nextInt(cities.size))))
  }

  override def ack(msgId: AnyRef) { println("OK: " + msgId)  }

  override def fail(msgId: AnyRef) { println("FAIL: " + msgId)  }

//  override def close():Unit {  }
}
