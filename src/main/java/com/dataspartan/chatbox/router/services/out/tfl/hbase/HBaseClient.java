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

@Repository
public class HBaseClient {

	private static final Logger log = LoggerFactory.getLogger(HBaseClient.class);

	public String getStatusSeverityDescription(StationsEnum station) throws Exception {
		try {
			String result = null;
			TableName tableName = TableName.valueOf("tfl-tube");
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.property.clientPort", "2181");
			conf.set("hbase.zookeeper.quorum", "hortonworks21");
			conf.set("zookeeper.znode.parent", "/hbase-unsecure");
			try (Connection conn = ConnectionFactory.createConnection(conf); Table table = conn.getTable(tableName);) {
				Result r = table.get(new Get(Bytes.toBytes("bakerloo")));
				result = Bytes
						.toString(r.getValue(Bytes.toBytes("status"), Bytes.toBytes("statusSeverityDescription")));
				System.out.println(r);
			}
			return result;
		} catch (Exception e) {
			log.error("Error accessing hbase for retrieving statusSeverityDescription.", e);
			throw e;
		}

	}

}
