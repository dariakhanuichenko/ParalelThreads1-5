package ua.kpi;

public class Main {

    public static Main output = new Main();

    public static void main(String[] args) {
        Runnable r = new Runnable1();
        Thread t1 = new Thread(r);
        Runnable r2 = new Runnable2();
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }

    private Object syncher = new Object();  // позволяет заблокировать доступ к методу или части кода, если его уже использует другой поток.
    private int state = 0; // 0 allows '-', 1 allows '|'
    private int counter = 0;

    public void printFirst(char pChar) {
        synchronized (syncher) {      // предотвращаем доступ к состоянию другой печати
            while (true) {
                if (state == 0) {     // разрешено '-'
                    System.out.print(pChar);
                    state = 1;        //меняем состояние
                    counter++;
                    if (counter > 50) {
                        System.out.println();
                        counter = 0;
                    }
                    syncher.notify(); // пробуждаем другие потоки
                    return;
                } else {              // запрещено '-'
                    try {
                        syncher.wait();  // ожидаем
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public void printSecond(char pInt) {
        synchronized (syncher) {
            while (true) {
                if (state == 1) {
                    System.out.print(pInt);
                    state = 0;
                    counter++;
                    if (counter > 50) {
                        System.out.println();
                        counter = 0;
                    }
                    syncher.notify();
                    return;
                } else {
                    try {
                        syncher.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}



