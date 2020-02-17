package com.wutao.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.reflect.Method;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2020/2/17 15:55
 */
public class UpdatePerson {

    public static void update()throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.wutao.aop.PersonService");

        CtMethod personFly = ctClass.getDeclaredMethod("personFly");

        //上面的insertBefore() 和 setBody()中的语句，如果你是单行语句可以直接用双引号，但是有多行语句的情况下，你需要将多行语句用{}括起来。
        // javassist只接受单个语句或用大括号括起来的语句块。
        personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");System.out.println(\"测试多行语句\");");
        personFly.insertAfter("System.out.println(\"成功落地\");");

        //新增一个方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType,"joinFriend",new CtClass[]{},ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        ctClass.addMethod(ctMethod);

        Object person = ctClass.toClass().newInstance();
        Method personFlyMethod = person.getClass().getMethod("personFly");
        personFlyMethod.invoke(person);

        Method joinFriend = person.getClass().getMethod("joinFriend");
        joinFriend.invoke(person);
    }

    public static void main(String[] args) {
        try {
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
