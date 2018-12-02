package test;

import entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 17:09
 */
public class Test {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext2.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext3.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());

    }
}
