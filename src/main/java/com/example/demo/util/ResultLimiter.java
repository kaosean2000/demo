package com.example.demo.util;

import java.util.List;

public class ResultLimiter {
    int limit,page;

    public ResultLimiter(int limit, int page) {
        this.limit = limit;
        this.page = page;
    }
    public int getStartIndex(){
        return limit*page;
    }
    public int getLength(){
        return limit;
    }
    public int getEndIndex(){
        return getStartIndex()+limit;
    }

    public <T> List<T>  getLimitedList(List<T> list){
        return list.stream()
                .skip(getStartIndex())
                .limit(getLength())
                .toList();
    }
    public static <T> List<T>  getLimitedList(ResultLimiter limit, List<T> list){
        return limit == null ? list:limit.getLimitedList(list);
    }
}
