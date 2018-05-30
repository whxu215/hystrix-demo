package com.andy.hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandUsingRequestCacheTest {
  @Test
  public void testCommandUsingRequestCache() {
    HystrixRequestContext context = HystrixRequestContext.initializeContext();
    try {
      CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
      CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);

      assertTrue(command2a.execute());
      // this is the first time we've executed this command with
      // the value of "2" so it should not be from cache
      assertFalse(command2a.isResponseFromCache());

      assertTrue(command2b.execute());
      // this is the second time we've executed this command with
      // the same value so it should return from cache
      assertTrue(command2b.isResponseFromCache());
    } finally {
      context.shutdown();
    }

    // start a new request context
    context = HystrixRequestContext.initializeContext();
    try {
      CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
      assertTrue(command3b.execute());
      // this is a new request context so this
      // should not come from cache
      assertFalse(command3b.isResponseFromCache());
    } finally {
      context.shutdown();
    }
  }
}
