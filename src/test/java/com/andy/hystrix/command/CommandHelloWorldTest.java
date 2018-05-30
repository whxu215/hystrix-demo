package com.andy.hystrix.command;

import org.junit.Assert;
import org.junit.Test;

public class CommandHelloWorldTest {
  @Test
  public void testCommandHelloWorld() {
    Assert.assertEquals("Hello World!", new CommandHelloWorld("World").execute());
  }
}
