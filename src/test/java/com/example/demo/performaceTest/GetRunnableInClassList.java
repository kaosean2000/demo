package com.example.demo.performaceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRunnableInClassList implements GetRunnable{
    List<Class<? extends Runnable>> list = new ArrayList<>();

    public GetRunnableInClassList(Class<? extends Runnable> ...classes){
        list.addAll(List.of(classes));
    }
    @Override
    public Runnable getRunnable(PerformanceTestManager manager) {
        int id = manager.random.nextInt(list.size());
        try {
            var clazz = list.get(id);
            return clazz.getConstructor(PerformanceTestManager.class).newInstance(manager);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
