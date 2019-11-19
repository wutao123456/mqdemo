package sso.entity;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/11/1 22:35
 */
public class User {

    private int id;

    private String userName;

    private String password;

    private int expired;

    private int disabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}
