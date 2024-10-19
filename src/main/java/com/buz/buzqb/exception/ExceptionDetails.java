package com.buz.buzqb.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ExceptionDetails {

  List<String> getAddtionalData();

  static List<String> getMap(Map<String, String> additionalData) {
    List<String> results = new ArrayList<>();
    if (additionalData != null) {
      for (String key : additionalData.keySet()) {
        results.add(key + " : " + additionalData.get(key));
      }
    }
    return results;
  }
}
