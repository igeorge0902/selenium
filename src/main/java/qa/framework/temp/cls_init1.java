package main.java.qa.framework.temp;

public class cls_init1 {
    static class Data {
            private int month;
            private String name;
            Data(int i, String s) {
                    month = i;
                    name = s;
            }
    }
    Data months[] = {
            new Data(1, "January"),
            new Data(2, "February"),
            new Data(3, "March"),
            new Data(4, "April"),
            new Data(5, "May"),
            new Data(6, "June")
    };
    public static void main(String args[]) {
            final int N = 250000;
            cls_init1 x;
            Timer t = new Timer();
            for (int i = 1; i <= N; i++)
                    x = new cls_init1();
            t.print("data declared non-static");
    }
}
