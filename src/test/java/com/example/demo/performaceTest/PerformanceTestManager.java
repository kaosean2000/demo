package com.example.demo.performaceTest;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PerformanceTestManager {
    private final AtomicInteger resultCounter = new AtomicInteger();
    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    private final MockMvc mvc;
    private final long timeout;
    private final TimeUnit unit;
    private final int instanceCount;
    private long actualTime;
    private final GetRunnable getRunnable;
    protected final Random random = new Random();

    public PerformanceTestManager(MockMvc mvc, long timeout, TimeUnit unit, int instanceCount,GetRunnable getRunnable) {
        this.mvc = mvc;
        this.timeout = timeout;
        this.unit = unit;
        this.instanceCount = instanceCount;
        this.getRunnable = getRunnable;
    }
    public void startTest() throws InterruptedException {
        System.out.printf("效能測試  --%d秒的壓力測試，請稍候--\n",timeout);
        long start_time = System.currentTimeMillis();
        addThreadsAndExecute();
        waitForResult();
        actualTime = System.currentTimeMillis() - start_time;
        printScore();
    }
    private void waitForResult() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(timeout, unit);
    }
    private void addThreadsAndExecute() {
        for (int i = 0; i < instanceCount; i++){
            Runnable runnable = getRunnable.getRunnable(this);
            executor.execute(runnable);
        }
    }

    private void printScore(){
        System.out.printf("效能測試  --執行了%.2f秒，處理%d個請求，平均每秒%.2f請求，每個請求平均%d毫秒--\n",
                getActualTimeSecond(),getProcessedCount(),getScore(),getAvgProcessTime());
    }
    public double getActualTimeSecond(){
        return actualTime /1000.0;
    }
    public double getScore(){
        return getProcessedCount() / getActualTimeSecond();
    }
    public int getProcessedCount(){
        return resultCounter.get();
    }
    public int getAvgProcessTime(){
        return (int) ((double) actualTime / getProcessedCount());
    }
    protected MockMvc getMVC() {
        return mvc;
    }

    protected void done() {
        resultCounter.incrementAndGet();
    }
}
