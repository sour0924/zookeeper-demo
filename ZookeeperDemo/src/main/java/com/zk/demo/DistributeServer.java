/**
 * 
 */
package com.zk.demo;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author Administrator
 * 实际案例
 */
public class DistributeServer {
	
	private String connectString = "127.0.0.1:2181";
	private int sessionTimeout = 2000; 
	
	public static void main(String[] args) throws Exception {
		DistributeServer server = new DistributeServer();
		
		//1连接zk集群
		server.getConnect();
		//2注册节点
		//3业务逻辑处理
	}
	
	private void getConnect() throws Exception {
		// 创建连接
		new ZooKeeper(connectString, sessionTimeout, new Watcher() {

			public void process(WatchedEvent event) {
				// 设置监听
				
			}
			
		});
		
	}
	
}
