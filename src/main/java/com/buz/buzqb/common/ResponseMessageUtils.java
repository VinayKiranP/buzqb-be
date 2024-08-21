package com.buz.buzqb.common;

public class ResponseMessageUtils {
  public static final String INVALID_CREDENTIALS = "Email or Password is incorrect";
  public static final String DELIMETER = ", ";

  public static String getDeactivatedResourceMessage(String resource) {
    return resource + "is not Active";
  }

  public static String getFieldNotNullMessage(String key) {
    return key + " can't be empty.";
  }

  public static String getVerificationShouldBeCompletedMessage(String key) {
    return key + " Verification Should be Completed.";
  }

  public static String getDuplicateRecordMessage(String object, String key) {
    return object + " with Same "+ key +" already Exist";
  }
}
