package com.wutao;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception{
        //通过接口的方式调用生成的类对象
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath("D:\\DLH\\wutao_project\\boot\\common\\javassist-demo\\src\\main\\java");
        CtClass codeClassI = pool.get("com.wutao.PersonI");

        CtClass classP = pool.get("com.wutao.Person");
        classP.setInterfaces(new CtClass[]{codeClassI});

        PersonI person = (PersonI) classP.toClass().newInstance();
        System.out.println(person.getName());
        person.setName("xl");
        person.printName();
    }
}
