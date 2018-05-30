package com.andy.hystrix.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Future;

public class CommandHelloFailureTest {
  @Test
  public void testCommandHelloFailure() {
    Assert.assertEquals("Hello Failure World!", new CommandHelloFailure("World").execute());
  }
}
