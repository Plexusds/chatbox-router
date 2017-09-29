package com.dataspartan.chatbox.router.services.out.tfl.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dataspartan.chatbox.router.services.out.tfl.enums.StationsEnum;
import com.dataspartan.chatbox.router.utils.Pair;
import com.dataspartan.chatbox.router.utils.SystemUtil;

@Repository
public class HBaseClient {

	private static final Logger log = LoggerFactory.getLogger(HBaseClient.class);

	public Pair<String, Long> getStatusSeverityDescription(StationsEnum station) throws Exception {
		try {
			Pair<String, Long>  result = null;
			String hbaseZookeeper = SystemUtil.getEnv("ZOOKEEPER_SERVER","hortonworks21:2181");
			TableName tableName = TableName.valueOf("tfl-tube");
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", hbaseZookeeper.split(":")[0]);
			conf.set("hbase.zookeeper.property.clientPort", hbaseZookeeper.split(":")[1]);
			conf.set("zookeeper.znode.parent", "/hbase-unsecure");
			try (Connection conn = ConnectionFactory.createConnection(conf); Table table = conn.getTable(tableName);) {
				Result r = table.get(new Get(Bytes.toBytes(station.getId())));
				if (r.rawCells().length > 0) {
					String status = Bytes
							.toString(r.getValue(Bytes.toBytes("status"), Bytes.toBytes("statusSeverityDescription")));
					Long timestamp = r.rawCells()[0].getTimestamp();
					result = new Pair<String, Long>(status, timestamp);
					log.info(String.format("The status of the station %s at %d is %s", station.getName(), timestamp, status));
				} else {
					log.info(String.format("Status of the station %s not found in HBASE.", station.getName()));
				}
			}
			return result;
		} catch (Exception e) {
			log.error("Error accessing hbase for retrieving statusSeverityDescription.", e);
			throw e;
		}

	}

}
