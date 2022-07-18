A happens before B 说白了就是 A必须发生在B之前，不能被reorder到B之后

JMM 提供了一些happens before规则 来guarantee 正确编写的多线程程序的语义

JAVA Happens Before Guarantee is a set of restrictions on instruction reorderint to avoid instruction reordering breaking the Java visibility guarantees:

* End of synchronized block happens before entry into subsequent synchronized block which is synchronized on the same monitor object

* Thread.start() happens before its Runnable.run() method is called

* Several happens before guarantees in java.util.concurrent APIs

* Etc.

几个可见性案例：

在JMM中，如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须要存在happens-before关系。

>
两个操作之间具有happens-before关系，并不意味着前一个操作必须要在后一个 操作之前执行！happens-before仅仅要求前一个操作（执行的结果）对后一个操作可见，且前一 个操作按顺序排在第二个操作之前（the first is visible to and ordered before the second）。



```java
// JYYOS 指令重排导致的可见性问题
// 线程本地内存也会导致该可见性问题
class OutofOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    public static void main(String[] args)
            throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("(" + x + "," + y + ")");
    }
}

// 生产者消费者模式 下 指令重排、缓存导致可见性问题
class Frame{

}
class FrameExchanger {
    private long framesStoredCount=0;
    private long framesTakenCount=0;
    private volatile boolean hasNewFrame=false;
    private Frame frame=null;
    public void storeFrame(Frame frame){
        this.frame=frame;
        this.framesStoredCount++;
        this.hasNewFrame=true;
    }
    public Frame takeFrame(){
        while(!hasNewFrame);
        Frame newFrame=this.frame;
        this.framesTakenCount++;
        this.hasNewFrame=false;
        return newFrame;
    }
}

// 单例模式下 指令重排序导致部分实例化问题
class Singleton {
    private static Singleton singleton = null;
    public int f1 = 1;   // 触发部分初始化问题
    public int f2 = 2;
    private Singleton() {
    }
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                // must be a complete instance
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```