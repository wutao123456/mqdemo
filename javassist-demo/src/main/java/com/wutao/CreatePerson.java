package com.wutao;

import javassist.*;

import java.lang.reflect.Method;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2020/2/16 21:39
 */
public class CreatePerson {

    public static void createPerson()throws Exception{
        //获得对象容器

        //需要注意的是 ClassPool 会在内存中维护所有被它创建过的 CtClass，当 CtClass 数量过多时，会占用大量的内存，
        // API中给出的解决方案是 有意识的调用CtClass的detach()方法以释放内存。
        ClassPool pool = ClassPool.getDefault();

        //创建一个空类
        CtClass cc = pool.makeClass("com.wutao.Person");

        //新增一个字段private String name
        CtField ctField = new CtField(pool.get("java.lang.String"),"name",cc);
        //设置访问级别
        ctField.setModifiers(Modifier.PRIVATE);
        //设置初始值
        cc.addField(ctField,CtField.Initializer.constant("xiaoming"));

        //生成get、set方法
        cc.addMethod(CtNewMethod.setter("setName",ctField));
        cc.addMethod(CtNewMethod.getter("getName",ctField));

        //添加无参构造函数
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{},cc);
        ctConstructor.setBody("{name=\"xiaohong\";}");
        cc.addConstructor(ctConstructor);

        //添加有参构造
        ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String")},cc);
        // $0=this / $1,$2,$3... 代表方法参数
        ctConstructor.setBody("{$0.name = $1;}");
        cc.addConstructor(ctConstructor);

        CtMethod ctMethod = new CtMethod(CtClass.voidType,"printName",new CtClass[]{},cc);
        ctMethod.setBody("{System.out.println(name);}");
        ctMethod.setModifiers(Modifier.PUBLIC);
        cc.addMethod(ctMethod);

        //根据CtClass生成 .class 文件；
        cc.writeFile("D:\\DLH\\wutao_project\\boot\\common\\javassist-demo\\target\\classes");


        //调用生成的类对象
        //通过反射的方式调用
        Object person = cc.toClass().newInstance();
        Method method = person.getClass().getMethod("setName",String.class);
        method.invoke(person,"wucao");

        Method printMethod = person.getClass().getMethod("printName");
        printMethod.invoke(person);


//        System.err.println("==========interfaces=============");
//        pool.appendClassPath("D:\\DLH\\wutao_project\\boot\\javassist-demo\\src\\main\\java");
//        CtClass in = pool.get("com.wutao.PersonI");
//
//        CtClass classP = pool.get("com.wutao.Person");
//        classP.defrost();
//        classP.setInterfaces(new CtClass[]{in});
//
//        PersonI personI = (PersonI) classP.toClass().newInstance();
//        System.out.println(personI.getName());
//        personI.setName("xl");
//        personI.printName();
    }

    public static void main(String[] args) {
        try {
            createPerson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
