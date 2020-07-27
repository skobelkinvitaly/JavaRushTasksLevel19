package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        ArrayList<String> list1 = new ArrayList<>();
        BufferedReader readerFile = new BufferedReader(new FileReader(file1));
        while (readerFile.ready()) {
            String s = readerFile.readLine();
            list1.add(s);
        }
        readerFile.close();
        System.out.println(list1);

        ArrayList<String> list2 = new ArrayList<>();
        BufferedReader readerFile2 = new BufferedReader(new FileReader(file2));
        while (readerFile2.ready()) {
            String s2 = readerFile2.readLine();
            list2.add(s2);
        }
        readerFile2.close();
        System.out.println(list2);

        while ((list1.size() > 0)&(list2.size() > 0)){
            if (list1.get(0).equals(list2.get(0))) {
                lines.add(new LineItem(Type.SAME, list1.get(0)));
                list1.remove(0);
                list2.remove(0);
            }
            else if (list1.get(1).equals(list2.get(0))){
                lines.add(new LineItem(Type.REMOVED, list1.get(0)));
                list1.remove(0);
            }
            else {
                lines.add(new LineItem(Type.ADDED, list2.get(0)));
                list2.remove(0);
            }
        }

        if (list1.size() == 0){
            while (list2.size()>0){
                lines.add(new LineItem(Type.ADDED, list2.get(0)));
                list2.remove(0);
            }
        }
        else {
            while (list1.size()>0){
                lines.add(new LineItem(Type.REMOVED, list2.get(0)));
                list1.remove(0);
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i).line + lines.get(i).type.name());

        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
