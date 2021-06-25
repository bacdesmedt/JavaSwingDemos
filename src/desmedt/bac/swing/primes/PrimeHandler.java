package desmedt.bac.swing.primes;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeHandler implements Runnable{
    
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicInteger range;
    private final AtomicInteger count;
    
    UIUpdater updater;
    
    public PrimeHandler(UIUpdater updater, int range) {
        this.range = new AtomicInteger(range + 1);
        this.count = new AtomicInteger(0);
        this.updater = updater;
    }
    
    interface UIUpdater{
        void onStart(int countGuess);
        void onProgressUpdate(int prime, int count);
        void onFinished(boolean interrupted, int count);
    }
    
    public void start() {
        updater.onStart(getPrimesCountGuess(range.get()));
        worker = new Thread(this);
        worker.start();
    }
    
    public void stop(boolean interrupted) {
        running.set(false);
        updater.onFinished(interrupted,count.get());
    }
    
    @Override
    public void run() {
        running.set(true);
        int i = 2;
        
        while (running.get() && i < range.get()) {
            if (isPrime(i)) {
                updater.onProgressUpdate(i, count.incrementAndGet());
            }
    
            if (i == 2) i--;    //-- only check uneven numbers larger than 2
            
            i += 2;
        }
        
        stop(false);
    }
    
    public static boolean isPrime(int number) {
        
        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;
        
        return number > 1;
    }
    
    private static int getPrimesCountGuess(int range) {
        char[] chars = Integer.toString(range).toCharArray();
        return (int) Math.ceil(range / ((chars.length - 1) * 2D));
    }
}
