import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.it.netty.rpc.service.Person;
import com.it.netty.rpc.service.PersonService;


public class springClient {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consume.xml");
		final PersonService bean = (PersonService) applicationContext.getBean(PersonService.class);
		Person name = bean.getName();
		System.out.println(name.getName());
		
//		long currentTimeMillis = System.currentTimeMillis();
//		for(int i=0;i<10000;i++){
//			Person name = bean.getName();
//			System.out.println(name.getName());
//		}
//		long currentTimeMillis1 = System.currentTimeMillis();
//		System.out.println("共花费========================="+(currentTimeMillis1-currentTimeMillis));
	}
}
