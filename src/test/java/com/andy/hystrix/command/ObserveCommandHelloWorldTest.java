package com.andy.hystrix.command;

import org.junit.Assert;
import org.junit.Test;
import rx.Observable;

public class ObserveCommandHelloWorldTest {
  @Test
  public void testObserveCommandHelloWorld() {
    Observable<String> obCommand = new ObserveCommandHelloWorld("World").observe();

    Assert.assertEquals("Hello World!", obCommand.toBlocking().single());

    obCommand.subscribe((v) -> {
      System.out.println("onNext:" + v);
    });
  }
}
