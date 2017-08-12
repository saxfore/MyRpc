package com.it.netty.rpc.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.it.netty.rpc.message.MsgRequest;

public class ReflectUtils {
	protected static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
	public static Object getReturn(MsgRequest request){
		
		String className = request.getClassName();
		String methodName = request.getMethodName();
		Object[] params = request.getParams();
		try {
			Class<?> forName = Class.forName(className);
			Object newInstance = forName.newInstance();
			Method method = forName.getMethod(methodName, request.getParamsType());
			return  method.invoke(newInstance, params);
		} catch (ReflectiveOperationException  e) {
			// TODO Auto-generated catch block
			logger.error(ReflectUtils.class.getName()+" error {}" ,e.getMessage());
		} 
		return null;
	}
}
