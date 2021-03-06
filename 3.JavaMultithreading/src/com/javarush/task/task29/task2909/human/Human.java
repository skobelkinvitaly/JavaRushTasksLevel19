package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private List<Human> children = new ArrayList<>();
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
//    protected int course;

//    protected int[] size;
    Size size = new Size(190, 95);
    public class Size{
        public int height;

    public Size(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int weight;
}

//    protected boolean isSoldier;

//    public static final int FIRST = 1;
//    public static final int SECOND = 2;
//    public static final int THIRD = 3;
//    public static final int FOURTH = 4;
    private BloodGroup bloodGroup;

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    //
//    public void setBloodGroup(int code) {
//        bloodGroup = code;
//    }
//
//    public int getBloodGroup() {
//        return bloodGroup;
//    }

    public Human(String name, int age) {
//        this.isSoldier = isSoldier;
        this.age = age;
        this.name = name;
        this.id = nextId;
        nextId++;
    }
    public void live() {
//        if (this instanceof Soldier)
//            fight();
    }
    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public int getCourse() {
//        return course;
//    }




    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

//    public void printSize() {
//        System.out.println("Рост: " + size[0] + " Вес: " + size[1]);
//    }
    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public void addChild(Human human){
        children.add(human);
    }
    public void removeChild(Human human){
        children.remove(human);
    }
    public  String getPosition(){
        return "Человек";
    }
    public void printData(){
        System.out.println(getPosition() + ": " + name);
    }
}