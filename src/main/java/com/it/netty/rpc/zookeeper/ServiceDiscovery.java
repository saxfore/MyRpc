package com.it.netty.rpc.zookeeper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.alibaba.fastjson.JSON;
import com.it.netty.rpc.zookeeper.base.URI;

public class ServiceDiscovery {
	public  static Map<String,URI> dataList = new ConcurrentHashMap<String,URI>();
	public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	public  static void watchNode(final ZooKeeper zk) {
		try {
			List<String> nodeList = zk.getChildren("/", new Watcher() {
				public void process(WatchedEvent event) {
					if (event.getType() == Event.EventType.NodeChildrenChanged) {
						watchNode(zk);
					};
					if (event.getType() == Event.EventType.NodeCreated) {
						watchNode(zk);
					};
					if (event.getType() == Event.EventType.NodeDeleted) {
						watchNode(zk);
					};
					if (event.getType() == Event.EventType.NodeDeleted) {
						watchNode(zk);
					};
					
				}
			});
			for (String node : nodeList) {
				byte[] bytes = zk.getData( "/" + node, false, null);
				dataList.put(node,getBytesToURI(bytes));
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public static URI getBytesToURI(byte[] bytes){
	    	return JSON.parseObject(new String(bytes), URI.class);
	    }
	public static URI findURIByPath(String path) {
		if(!dataList.isEmpty()){
			return dataList.get(path);
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public static URI findURIByThread(){
		try {
			if(dataList!=null){
				if(dataList.get(threadLocal.get())!=null){
					return dataList.get(threadLocal.get());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			threadLocal.remove();
		}
		return null;
	}
	
}
