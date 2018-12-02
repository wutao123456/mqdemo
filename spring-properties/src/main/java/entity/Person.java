package entity;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 17:07
 */
public class Person {

    private String user;

    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
