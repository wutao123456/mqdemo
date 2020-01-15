package com.dlh;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Hello world!
 *
 */
public class App {
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);
        inst.addTransformer(new DefineTransformer(),true);
    }

    /**
     * 如果不存在premain(String agentArgs, Instrumentation inst)
     * 则会执行premain(String agentArgs)
     * @param agentArgs
     */
    public static void premain(String agentArgs){
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }

    static class DefineTransformer implements ClassFileTransformer{
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load class " + className);
            return classfileBuffer;
        }
    }
}
