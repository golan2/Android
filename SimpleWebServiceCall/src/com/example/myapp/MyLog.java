package com.example.myapp;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    13/03/2015 08:29
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MyLog {

  private static final String IZIK = "Izik";
  private static final String CLASS_NAME = MyLog.class.getSimpleName()+".java";
  private static final int ADDED_DEPTH = 4;  //we add a few methods to the CallStack like [generatePrefix] and [log] itself

  public void error(String s, Throwable t) {
    log(LogLevel.ERROR, s, t);
  }

  public enum LogLevel {ERROR, WARN, INFO, DEBUG}

  private static final String[] arrExcludedFileNames = {
      MyLog.class.getSimpleName()+".java"
      ,"Thread.java"
      ,"Method.java"
      ,"AppMain.java"
      ,"DelegatingMethodAccessorImpl.java"
      ,"NativeMethodAccessorImpl.java"
  };

  private static final Set<String> excludedFileNames = new HashSet<>(Arrays.asList(arrExcludedFileNames));



  private static String generatePrefix() {
    //noinspection StringBufferReplaceableByString
    StringBuilder buf = new StringBuilder();
    buf.append("[").append(System.currentTimeMillis()).append("] ~ ");
    buf.append("[").append(Thread.currentThread().getName()).append("] ~ ");
    buf.append("[").append(getClassNameAndLineNumber()).append("] ~ ");
    return  buf.toString();
  }

  private static String getClassNameAndLineNumber() {
    StackTraceElement stackTraceElement = findCallerMethodInCallStack();
    String classNameAndLineNumber;
    if (stackTraceElement!=null) {
      String className = stackTraceElement.getFileName();
      int lineNumber = stackTraceElement.getLineNumber();
      classNameAndLineNumber = className + ":" + lineNumber;
    }
    else {
      classNameAndLineNumber="?";
    }
    return classNameAndLineNumber;
  }

  /**
   * The caller to {@link com.example.myapp.MyLog#log} is in the Call Stack
   * Iterate the Call Stack and find it there in order to get the ClassName and LineNumber later on
   * @return the stackTraceElement where the "real" caller is
   */
  private static StackTraceElement findCallerMethodInCallStack() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    int logFileEncountered = 0;

    for (int i = 0; i < stackTrace.length && logFileEncountered<ADDED_DEPTH; i++) {
      StackTraceElement ste = stackTrace[i];
      if ( ste.getFileName().equals(CLASS_NAME) ) {
        logFileEncountered++;
      }
      if (logFileEncountered==ADDED_DEPTH) {
        return stackTrace[i+1];
      }
    }
    return null;
  }

  public static void log(Throwable t) {
    log(LogLevel.ERROR, "", t);
  }

  public static void log(LogLevel logLevel, String message, Throwable t) {
    String stackTrace = Log.getStackTraceString(t);
    log(logLevel, message + "\n" + stackTrace);
  }

  public static void log(LogLevel logLevel, String message) {

    String msg = generatePrefix() + message;
    if (LogLevel.DEBUG==logLevel) {
      Log.d(IZIK, msg);
    }
    else if (LogLevel.INFO==logLevel) {
      Log.i(IZIK, msg);
    }
    else if (LogLevel.WARN==logLevel) {
      Log.w(IZIK, msg);
    }
    else if (LogLevel.ERROR==logLevel) {
      Log.e(IZIK, msg);
    }
    else {
      throw new IllegalArgumentException("Incorrect value of LogLevel:" + logLevel);
    }
  }
}
