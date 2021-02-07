package ua.kpi;

public class Runnable2 implements Runnable{
    public void run(){
        for(char i=0;i<=10000;i++) {
            Main.output.printSecond('|');
        }
    }
}
