package examples

import backtype.storm.topology.TopologyBuilder
import backtype.storm.tuple.Fields
import backtype.storm.{Config, LocalCluster}
import demo.topology.{CatBolt, CitySpout}

/**
 * Created by wolf on 3/16/15.
 */
object LocalTopology {

  def main(args: Array[String]): Unit = {

    val builder = new TopologyBuilder
    builder.setSpout("random-cities", new CitySpout, 2)
    builder.setBolt("city-greet", new CatBolt, 2).fieldsGrouping("random-cities", new Fields("city"))
      //.shuffleGrouping("random-cities")

    val conf = new Config
    conf.setDebug(true)
    conf.setMaxTaskParallelism(2)

    val cluster = new LocalCluster
    cluster.submitTopology("city-greetings", conf, builder.createTopology)
    Thread sleep 2000
    cluster.shutdown
  }

}
