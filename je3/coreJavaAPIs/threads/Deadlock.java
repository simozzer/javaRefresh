package je3.coreJavaAPIs.threads;

public class Deadlock {

    public static void main(String[] args) {
        final Object res1 = "res1";
        final Object res2 = "res2";
        final Object res3 = "res3";

        Thread t1 = new Thread() {
            public void run() {
                synchronized (res1) {
                    System.out.println("Thread1 : locked res 1");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    synchronized (res2) {
                        System.out.println("Thread 1: locked res 2");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }

                    synchronized (res3) {
                        System.out.println("Thread1: locked res 3");
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                synchronized (res2) {
                    System.out.println("Thread2 : locked res 2");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    synchronized (res1) {
                        System.out.println("Thread 2: locked res 1");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }

                    synchronized (res3) {
                        System.out.println("Thread2: locked res 3");
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                synchronized (res3) {
                    System.out.println("Thread3 : locked res 3");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    synchronized (res2) {
                        System.out.println("Thread 3: locked res 2");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                        }
                    }

                    synchronized (res1) {
                        System.out.println("Thread3: locked res 1");
                    }
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
    }

}
