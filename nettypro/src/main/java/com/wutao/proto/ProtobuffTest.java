package com.wutao.proto;

/**
 * @author wutao
 * @date 2019/12/10
 */
public class ProtobuffTest {

    public static void main(String[] args) throws Exception{
        ProtoDemo.Student.PhoneNumber.Builder phoneNumberBuilder = ProtoDemo.Student.PhoneNumber.newBuilder();
        phoneNumberBuilder.setType(ProtoDemo.Student.PhoneType.HOME);
        phoneNumberBuilder.setNumber("13163314881");

        ProtoDemo.Student.PhoneNumber phoneNumber = ProtoDemo.Student.PhoneNumber.newBuilder().setType(ProtoDemo.Student.PhoneType.WORK).setNumber("17501678967").build();
        ProtoDemo.Student student = ProtoDemo.Student.newBuilder().setId(1).setName("test").setEmail("test@163.com").addPhone(phoneNumberBuilder).addPhone(phoneNumber).build();

        byte[] student2ByteArray = student.toByteArray();

        ProtoDemo.Student student2 = ProtoDemo.Student.parseFrom(student2ByteArray);
        System.out.println(student2.toString());
    }
}
