import java.util.concurrent.*;
class Bank{
    private int balance=0;

    public synchronized void deposit(){ // without this keyowrd the count chnages
        balance++;
    }
    public int getBalance(){
        return balance;
    }
}

class Worker extends Thread {
    private Bank b;

    Worker(Bank b){
        this.b = b;
    }

    public void run(){
        for(int i=0;i<1000;i++){
            b.deposit();
        }
    }
}

//Inter-Thread Communication Example --> producer consumer problem

class resource{
    int r;
    public boolean valueSet=false;
    public synchronized void  setR(int r){
        while(valueSet){
            try{ wait(); }catch(Exception e){}
        }
        System.out.println("Set :"+r);
        this.r=r;
        valueSet=true;
        notify();
    }
    public synchronized void getR(){
        while(!valueSet){
            try{ wait(); }catch(Exception e){}
        }
        System.out.println("Get : "+r);
        valueSet=false;
        notify();
    }
}

class Producer extends Thread{
    resource r;
    Producer(resource r){
        this.r=r;
    }
    public void  run(){
        int i=0;
        while(true){
            r.setR(i++);
            try{Thread.sleep(1000);} catch (Exception e){}
        }
    }
}

class Consumer extends  Thread{
    resource r;
    Consumer(resource r){
        this.r=r;
    }
    public void run(){
        while(true){
            r.getR();
            try{Thread.sleep(1000);} catch (Exception e){}
        }
    }
}





public class Threadings {
    public static void main(String[] args){

//        Bank b = new Bank();
//        Worker w1 = new Worker(b); // Thread is created..
//        Worker w2 = new Worker(b);
//        w1.start(); //thread started..
//        w2.start();
//        try{
//            w1.join();
//            w2.join(); //running concurrently one after another not at that same time on the shared resoruce
//        }
//        catch(InterruptedException e){
//            System.out.println(e);
//        }
//        System.out.println("count is : "+b.getBalance());
//
//
//        resource r = new resource();
//        Producer p = new Producer(r);
//        Consumer c = new Consumer(r);
//
//        p.start();
//        c.start();


        //Executor Framework , working of callable and future

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Define a task using Callable
        Callable<Integer> task =  () ->   {
            System.out.println("thread is calculating");
            Thread.sleep(1000);
            return 42;
        };

        //Submit the task and get a Future
        System.out.println("Submitting the task");
        Future<Integer> future = executor.submit(task);

        System.out.println("Main thread is free to do other things!");

        try {
            Integer result = future.get();
        System.out.println("Result received: " + result);
        }
        catch (InterruptedException | ExecutionException e){}

        //Shutdown
        executor.shutdown();
    }
}
