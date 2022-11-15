package com.cumt.studentArrange;

import java.util.ArrayList;
import java.util.Scanner;

public class studentArrange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        ArrayList<student> list = new ArrayList<>();
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
                    case 1:
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
                        break;
                    case 2 :
                        System.out.println("请输入学生新姓名");
                        String name = sc.next();
                        list.get(index).setName(name);
                        break;
                    case 3 :
                        System.out.println("请输入学生新年龄");
                        int age = sc.nextInt();
                        list.get(index).setAge(age);
                        break;
                    case 4 :
                        System.out.println("请输入学生地址");
                        String address = sc.next();
                        list.get(index).setAddress(address);
                        break;
                    case 5 :
                        flag = false;
                        break;
                    default :
                        System.out.println("输入非法，请重新输入");
                }
            }
        }
    }



    public static int seek(ArrayList<student> list,String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))return i;
        }
        return -1;
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

