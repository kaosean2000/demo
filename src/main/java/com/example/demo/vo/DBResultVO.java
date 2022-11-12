package com.example.demo.vo;

import java.util.List;
import java.util.Map;

public class DBResultVO {
    public DBResultVO(List<Map<String, Object>> result) {
        this.result = result;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    List<Map<String, Object>> result;
}
