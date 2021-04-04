package com.ensta.librarymanager.exception;

public class ServiceException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = -8258850051650712231L;

  public ServiceException() {
    super();
  }

  public ServiceException(String message) {
    super();
    System.out.println(message);
  }
}
