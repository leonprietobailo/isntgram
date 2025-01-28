package com.leonprieto.ig.isntgram_api.service.response;

public class GenericApiResponse {
private final boolean success;
private final String message;

  public GenericApiResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }
}
