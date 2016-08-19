package ru.sbt.calculate;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator{

    @Override
    public int doHardWork(Integer a, int b, String c) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            return a * b / c.length();
    }

    @Override
    public int doHardWork(Integer a, int b) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }

    @Override
    public List<String> doList(int a) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < a; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("...");
        }
        return list;
    }

    @Override
    public List<Integer> doList1(int a, String b) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < a; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(b.length());
        }
        return list;
    }
}
