package com.zk.demo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * zk  test
 * @author Administrator
 *
 */
public class ZkTest {
	
	private String connectString = "127.0.0.1:2181";//连接端口
	
	private int sessionTimeout = 2000;//超时时间
	
	private ZooKeeper zKClient;//创建zk客户端
	
	/**
	 * 创建节点之前,初始化
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		zKClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			public void process(WatchedEvent event) {
				
				System.out.println("-----start-------");
				//设置监听
				List<String> children;
				try {
					children = zKClient.getChildren("/", true);//true表示监听
					//遍历拿到每一个节点
					for (String child : children) {
						System.out.println(child);
					}
					
					System.out.println("-----end-------");
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				//Thread.sleep(5000);
			}
			
		});
	}
	
	/**
	 * 创建节点
	 * @throws Exception
	 */
	@Test
	public void createNode() throws Exception {
		String path = zKClient.create("/test1", "hello test1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(path);
	}
	
	/**
	 * 获取节点并监控节点变化(可设置)
	 * 这里并不能实时监听节点变化,需要放入process线程中
	 * 目前没有监听,待续...
	 * @throws Exception
	 */
	@Test
	public void getDataAndWatch() throws Exception{
		//false表示不监听,反之监听
		List<String> children = zKClient.getChildren("/", false);
		for (String child : children) {
			System.out.println(child);
		}
		
		Thread.sleep(5000);
	}
	
	/**
	 * 判断节点是否存在
	 * @throws Exception
	 */
	@Test
	public void exist() throws Exception {
		Stat stat = zKClient.exists("/test1", false);
		System.out.println(stat==null?"not exist":"exist");
	}
}
