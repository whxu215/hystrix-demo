package com.andy.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandUsingSemaphoreIsolation extends HystrixCommand<String> {
  private final int id;

  public CommandUsingSemaphoreIsolation(int id) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
            // since we're doing an in-memory cache lookup we choose SEMAPHORE isolation
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
    this.id = id;
  }

  @Override
  protected String run() {
    // a real implementation would retrieve data from in memory data structure
    return "ValueFromHashMap_" + id;
  }

}
