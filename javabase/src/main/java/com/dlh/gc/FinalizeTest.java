package com.dlh.gc;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/10 23:26
 */
public class FinalizeTest {

    public static void main(String[] args) {

        Person p1 = new FinalizeTest().new Person();
        p1.setId(1);
        p1 = null;
        System.gc();


    }

    class Person{
        private int id;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 对象被垃圾回收之前调用该方法
         * @throws Throwable
         */
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Person Object "+ id + " is disposed");
        }
    }
}
