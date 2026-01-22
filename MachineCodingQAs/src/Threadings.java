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

public class Threadings {
    public static void main(String[] args){

        Bank b = new Bank();
        Worker w1 = new Worker(b); // Thread is created..
        Worker w2 = new Worker(b);


        w1.start(); //thread started..
        w2.start();

        try{

            w1.join();
            w2.join(); //running concurrently one after another not at that same time on the shared resoruce
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("count is : "+b.getBalance());

    }
}
