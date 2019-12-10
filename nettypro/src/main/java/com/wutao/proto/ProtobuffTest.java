package com.wutao.proto;

/**
 * @author wutao
 * @date 2019/12/10
 */
public class ProtobuffTest {

    public static void main(String[] args) throws Exception{
        ProtoDemo.Student student = ProtoDemo.Student.newBuilder().setId(1).setName("test").setEmail("test@163.com").build();

        byte[] student2ByteArray = student.toByteArray();

        ProtoDemo.Student student2 = ProtoDemo.Student.parseFrom(student2ByteArray);
        System.out.println(student2.toString());
    }
}
