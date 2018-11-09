package com.dlh.designpattern.builder;

/**
 * 建造者模式
 */
public class Builder {

    static class Student{
        String name = null ;
        int number = -1 ;
        String sex = null ;
        int age = -1 ;
        String school = null ;

        //利用建造器作为参数来建造Student对象
        static class StudentBuilder{
            String name = null ;
            int number = -1 ;
            String sex = null ;
            int age = -1 ;
            String school = null ;

            public StudentBuilder setName(String name) {
                this.name = name;
                return this;
            }

            public StudentBuilder setNumber(int number) {
                this.number = number;
                return this;
            }

            public StudentBuilder setSex(String sex) {
                this.sex = sex;
                return this;
            }

            public StudentBuilder setAge(int age) {
                this.age = age;
                return this;
            }

            public StudentBuilder setSchool(String school) {
                this.school = school;
                return this;
            }

            public Student build(){
                return new Student(this);
            }

            public Student build1(){
                return  new Student(name,number,sex,age,school);
            }

        }

        public Student(StudentBuilder builder) {
            this.name = builder.name;
            this.number = builder.number;
            this.sex = builder.sex;
            this.age = builder.age;
            this.school = builder.school;
        }

        public Student(String name, int number, String sex, int age, String school) {
            this.name = name;
            this.number = number;
            this.sex = sex;
            this.age = age;
            this.school = school;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    ", school='" + school + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        Student a = new Student.StudentBuilder().setName("wutao").build();
        Student b = new Student.StudentBuilder().setName("xl").setSex("2").build1();
        System.out.println(a.toString());
        System.out.println(b.toString());
    }

}
