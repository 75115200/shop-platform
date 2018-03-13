package test;

import java.util.ArrayList;
import java.util.List;

public class TestCast {
    public static void main(String[] args) {
    }

    private static List<Object> get() {
        List<Object> lists = new ArrayList();
        String s = "aaaa";
        lists.add(s);
        return lists;
    }
}
