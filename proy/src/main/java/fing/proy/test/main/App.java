package fing.proy.test.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fing.proy.test.get.SimpleCoapClient;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
        AbstractApplicationContext  context = new ClassPathXmlApplicationContext("app-context.xml");
        
//        SimpleCoapClient client = context.getBean(SimpleCoapClient.class);
//        
//        String result = client.doGet("coap://[aaaa::212:7408:8:808]:5683/sensores/temperatura");
//        System.out.print("The get result is: " + result + "\n");
//        
//        client.doPost("coap://[aaaa::212:7408:8:808]:5683/test", "hola");
        
        System.out.print("READY!");
        

    }
}
