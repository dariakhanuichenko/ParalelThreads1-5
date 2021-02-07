package ua.kpi;

public
class Runnable1 implements Runnable{
    public void run(){
        for(int i=0;i<=10000;i++) {
            Main.output.printFirst('-');
        }
    }
}
