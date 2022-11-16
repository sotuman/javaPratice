package com.cumt.studentArrange;

public class User {
    String username;
    String password;
    String identify;
    String phone;

    public User() {
    }

    public User(String username, String password, String identify, String phone) {
        this.username = username;
        this.password = password;
        this.identify = identify;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentify() {
        return identify;
    }

    public String getPhone() {
        return phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void show(){
        System.out.println("用户名："+this.username);
        System.out.println("密码："+this.password);
        System.out.println("身份证："+this.identify);
        System.out.println("电话号码："+this.phone);
    }
}
