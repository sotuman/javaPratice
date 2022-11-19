package com.cumt.studentArrange;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class studentArrange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //登录界面
        boolean prepare = true;
        boolean flag = true;
        ArrayList<student> list = new ArrayList<>();
        ArrayList<User> userlist = new ArrayList<>();
        while(prepare){
            menu0();
            int choose = sc.nextInt();
            switch (choose) {
                case 1 -> {if(login(userlist)) prepare = false;}//登录，成功则跳出循环进入管理系统
                case 2 -> userlist.add(userRegister(userlist));//创建一个用户并纳入库
                case 3 -> {if(retrieve(userlist)) System.out.println("重置密码成功");}//重置密码
                default -> System.out.println("输入非法，请重新输入");
            }
        }

        //管理系统
        while (flag) {
            menu1();
            int input = sc.nextInt();
            switch (input) {
                case 1 -> seekStudent(list);//查询
                case 2 -> addStudent(list);//增加
                case 3 -> deleteStudent(list);//删除
                case 4 -> changeStudent(list);//修改
                case 5 -> flag = false;
                default -> System.out.println("输入非法，请重新输入");
            }

        }
    }

    public static boolean retrieve(ArrayList<User> userlist){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String input = sc.next();
        String input2;
        int index = seekuser(userlist,input);
        if(index >= 0){
            System.out.println("请输入身份证");
            input = sc.next();
            System.out.println("请输入电话号码");
            input2 = sc.next();
            if(userlist.get(index).getIdentify().equals(input) && userlist.get(index).getPhone().equals(input2)){
                System.out.println("请输入新密码");
                input = sc.next();
                userlist.get(index).setPassword(input);
                return  true;
            }else{
                System.out.println("信息不一致，找回失败");
                return false;
            }
        }else{
            System.out.println("用户不存在,请先注册");
            return false;
        }
    }

    public static boolean login(ArrayList<User> userlist){
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while(count <3){
            System.out.println("你还有"+(3-count)+"次输入机会");
            System.out.println("请输入用户名");
            String input = sc.next();
            int index = seekuser(userlist,input);
            if(index >= 0){
                System.out.println("请输入密码");
                input = sc.next();
                if(userlist.get(index).getPassword().equals(input)){
                    String code = getcode();
                    System.out.println("请输入验证码");
                    System.out.println(code);
                    input = sc.next();
                    if(code.equals(input)){
                        return true;
                    }else{
                        System.out.println("验证码错误");
                        count++;
                    }
                }else{
                    System.out.println("密码错误");
                    count++;
                }
            }else{
                System.out.println("用户不存在，请注册或重新输入:1.注册2.重新输入");
                int choose = sc.nextInt();
                if(choose == 1) {
                    return false;
                }else{
                    count++;
                }
            }
        }
        System.out.println("登录失败，将返回初始页面");
        return false;
    }

    public static User userRegister(ArrayList<User> userlist){
        Scanner sc = new Scanner(System.in);
        User a = new User();//创建用户对象
        //验证用户名
        System.out.println("请输入用户名");
        while(!checkUsername(sc,a)){//检测合法性
                if(seekuser(userlist,a.getUsername()) == -1){//检测用户名唯一性
                    break;
                }else{
                    System.out.println("用户名已存在，请重新输入");
                    a.setUsername(null);
                }
            }
        //验证密码
        System.out.println("请输入密码");
        while (true){
            String input = sc.next();
            System.out.println("请确认密码");
            String input2 = sc.next();
            if(input2.equals(input)){
                a.setPassword(input);
                break;
            }else{
                System.out.println("两次密码不一致，请重新输入");
            }
        }
        //验证身份证
        System.out.println("请输入身份证号码");
        while(!checkIdentity(sc, a)) {
        }
        //验证号码
        System.out.println("请输入电话号码");
        while(!checkPhone(sc, a)){
        }
        System.out.println("注册成功");
        a.show();
        return a;
        }

    private static boolean checkPhone(Scanner sc, User a) {
        String input = sc.next();
        int len = input.length();
        if(len != 11){
            System.out.println("电话号码应有11位，请重新输入");
            return false;
        }//检查位数
        char first = input.charAt(0);
        if(first <= '0' || first > '9'){
            System.out.println("电话号码不合法，请重新输入");
            return false;
        }//检查合法性，开头是否大于0的数字
        for (int i = 0; i < len-1; i++) {
            char num = input.charAt(i);
            if(num <= '0' || num > '9'){
                System.out.println("电话号码不合法,请重新输入");
                return false;
            }
        }//检查合法性，是否全为数字
        a.setPhone(input);
        return true;
    }

    private static boolean checkIdentity(Scanner sc, User a) {
        String input = sc.next();
        int len = input.length();
        if(len != 18) {
            System.out.println("身份证号码应有18位，请重新输入");
            return false;
        }//检查位数
        char first = input.charAt(0);
        char end = input.charAt(len - 1);
        if (first <= '0' || first > '9') {
            System.out.println("身份证号码不合法,请重新输入");
            return false;
        }//检查首位是不为0的数字
        if ((end <= '0' || end > '9') && end != 'x' && end != 'X') {
            System.out.println("身份证号码不合法,请重新输入");
            return false;
        }//检查末位
        for (int i = 0; i < len - 2; i++) {
            char num = input.charAt(i);
            if (num < '0' || num > '9') {
                System.out.println("身份证号码前17位必须都是数字,请重新输入");
                return false;
            }
        }//检查中间是否全为数字
        a.setIdentify(input);//全部正确则记录
        return true;
    }

    public static void seekStudent(ArrayList<student> list){
        System.out.println("请输入查询id");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = seek(list,id);
        if(index == -1){
            System.out.println("不存在该学生");
        }else{
            System.out.print("id："+list.get(index).getId()+",");
            System.out.print("姓名："+list.get(index).getName()+",");
            System.out.print("年龄："+list.get(index).getAge()+",");
            System.out.println("地址："+list.get(index).getAddress());
        }
    }

    public static void addStudent(ArrayList<student> list){
        System.out.println("请输入增加的学生id");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        int index = seek(list,id);
        if(index == -1){
            student a = new student();
            a.setId(id);
            System.out.println("请输入学生姓名");
            String name = sc.next();
            a.setName(name);
            System.out.println("请输入学生年龄");
            int age = sc.nextInt();
            a.setAge(age);
            System.out.println("请输入学生地址");
            String address = sc.next();
            a.setAddress(address);
            list.add(a);
            System.out.println("添加成功");
        }else{
            System.out.println("已经存在该学生");
        }
    }

    public static void deleteStudent(ArrayList<student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的id");
        String id = sc.next();
        int index = seek(list,id);
        if(index == -1){
            System.out.println("不存在该学生，删除失败");
        }else{
            list.remove(index);
            System.out.println("删除成功");
        }
    }

    public static void changeStudent(ArrayList<student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入想修改的学生id");
        String id = sc.next();
        boolean flag = true;
        int index = seek(list, id);
        if (index == -1) {
            System.out.println("不存在该学生，修改失败");
        } else {
            while (flag) {
                menu2();
                int in = sc.nextInt();
                switch (in) {
                    case 1 -> {
                        System.out.println("请输入学生新id");
                        while (true) {
                            id = sc.next();
                            if (seek(list, id) == -1) {
                                list.get(index).setId(id);
                                break;
                            } else {
                                System.out.println("该id已存在，请重新输入");
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("请输入学生新姓名");
                        String name = sc.next();
                        list.get(index).setName(name);
                    }
                    case 3 -> {
                        System.out.println("请输入学生新年龄");
                        int age = sc.nextInt();
                        list.get(index).setAge(age);
                    }
                    case 4 -> {
                        System.out.println("请输入学生地址");
                        String address = sc.next();
                        list.get(index).setAddress(address);
                    }
                    case 5 -> flag = false;
                    default -> System.out.println("输入非法，请重新输入");
                }
            }
        }
    }

    public static boolean checkUsername(Scanner sc,User a){
        String username = sc.next();
        int length = username.length();
        if(length <3 || length > 15){
            System.out.println("用户名长度必须为3-15，请重新输入");
            return false;
        }
        int counts = 0;
        for (int i = 0; i < length; i++) {
            char u = username.charAt(i);
            if(u >= '0' && u <= '9'){
                continue;
            } else if ((u >='A' && u <= 'Z') || (u >='a' && u <= 'z')) {
                counts++;
            }else{
                System.out.println("用户名只能由字母或数字组成，请重新输入");
                return false;
            }
        }
        if(counts <= 0){
            System.out.println("用户名不能为纯数字，请重新输入");
            return false;
        }
        a.setUsername(username);
        return true;
    }

    public static int seek(ArrayList<student> list,String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))return i;
        }
        return -1;
    }

    public static int seekuser(ArrayList<User> list,String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUsername().equals(id))return i;
        }
        return -1;
    }

    public static String getcode(){
        Random r = new Random();
        StringBuilder str = new StringBuilder();
        int i;
        char a;
        for (i = 0; i <3; i++) {
            int num = r.nextInt(9);
            a = (char) ('0'+num);
            str.append(a);
        }
        for (; i < 5; i++) {
            int alpha = r.nextInt(25);
            a = (char) ('a'+alpha);
            str.append(a);
        }
        return str.toString();
    }

    public static void menu0() {
        System.out.println("欢迎来到学生管理系统");
        System.out.println("请选择操作1.登录2.注册3.忘记密码");
    }


    public static void menu1(){
        System.out.println("----学生管理系统----");
        System.out.println("---1.查询学生信息---");
        System.out.println("---2.增加学生信息---");
        System.out.println("---3.删除学生信息---");
        System.out.println("---4.修改学生信息---");
        System.out.println("  ---5.退出系统---");
    }

    public static void menu2(){
        System.out.println("----选择修改项目----");
        System.out.println("---1.修改学生id---");
        System.out.println("---2.修改学生姓名---");
        System.out.println("---3.修改学生年龄---");
        System.out.println("---4.修改学生地址---");
        System.out.println("  ---5.退出修改---");
    }
}

