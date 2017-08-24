package com.it.netty.rpc.romote;

import com.it.netty.rpc.Config;
import com.it.netty.rpc.message.Invocation;
import com.it.netty.rpc.message.Result;
import com.it.netty.rpc.message.URI;
import com.it.netty.rpc.proxy.RpcProxyClient;
import com.it.netty.rpc.service.Person;
import com.it.netty.rpc.service.PersonService;

public class TestClient {
	static DeafultNettyClientRemoteConnection s=DeafultNettyClientRemoteConnection.newInstance();
	
	public static void main(String[] args) {
//		Invocation invocation = new Invocation();
//		invocation.setProtocol("HESSIAN");
//		invocation.setTimeout(2000);
		URI uri = new URI();
		uri.setHost("127.0.0.1");
		uri.setPort(8096);
//		invocation.setUri(uri );
//		invocation.setInterfaceClass(PersonService.class);
//		invocation.setClassName("com.it.netty.rpc.service.PersonService");
//		Callback invokeAsync = s.invokeAsync(invocation);
//		
//		Result  object = invokeAsync.getObject();
//		System.out.println(object.getResultCode());
		
		Config.uri.addCache("com.it.netty.rpc.service.PersonService", uri);
		PersonService personService = RpcProxyClient.getProxy(PersonService.class);
		Person name = personService.getName();
	}
}
