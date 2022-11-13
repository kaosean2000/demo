package com.example.demo.util;

import java.util.List;
import java.util.Map;

public class DBResult {
    public DBResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    List<Map<String, Object>> result;
}
