package com.avinash.Springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//Ioc and DI
//IoC stands for Inversion of Control- it is a principle
//generally, it is the developers responsibility to create,maintain and destroy objects in the code
//but the main responsibility is to maintain the business logic in the application
//so Spring will take the responsibility of maintaining objects, we are giving the control to the Spring
//this principle is known as Inversion of Control (IoC)
//Spring will maintain a container inside JVM, where the objects are stored
//objects created and managed by Spring are named as "beans"
//DI stands for Dependency Injection
//Dependency Injection means inter linking each class
//for suppose, if we want to work with laptop object, we need cpu object, and that dependency will be injected by Spring


//Laptop class
@Component  //Annotation, tells to the spring to create class instance
class Laptop{
	public void start(){
		System.out.println("hello Windows...!");
	}
	public void shutdown(){
		System.out.println("Shutting down...!");
	}
}

//Sim class
@Component
class Sim{
	public void call(){
		System.out.println("Calling...!");
	}
}

//Mobile class
@Component
class Mobile{

//	Sim s = new Sim();
	//creating a instance is not our task in Spring
	//hence we will annotate the keyword @Autowired
	@Autowired
	Sim s;
	public void makecall(){
		s.call();
	}
}

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {

		//SpringApplication is a class
		//run() is a method, which starts our Spring application and it returns ConfigurableApplicationContext object
		//normally we will use new keyword to create object
		//but here we will use IoC principle and give control to Spring
		//first we must tell to Spring that, you must create an instance of this class by annotation "@Component"
		//later we will get that instance by using "getBean()" method
		//getBean() method will take class name as parameter
		//we will apply that getBean() method on ApplicationContext object
		//as above discussed the run() method return ConfigurableApplicationContext object and it will extends the ApplicationContext
		//as ConfigurableApplicationContext is a child to ApplicationContext,so we will upcast it .we can apply on it

		ApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
		Laptop l1 = context.getBean(Laptop.class);
		l1.start();
		l1.shutdown();

		//now lets create classes, mobile and sim
		//to run a call in mobile we need simcard
		//here we need to insert the sim object into mobile class
		//this is known as dependency injection
		// we will achieve this by an annotation "@Autowired"

		Mobile m = context.getBean(Mobile.class);
		m.makecall();
	}

}
