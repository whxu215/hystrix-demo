package com.andy.hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ObserveCommandHelloWorld extends HystrixObservableCommand<String> {
  private final String name;

  public ObserveCommandHelloWorld(String name) {
    super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    this.name = name;
  }

  @Override
  protected Observable<String> construct() {
    return Observable.create((Subscriber<? super String> observer) -> {
      try {
        if (!observer.isUnsubscribed()) {
          // a real example would do work like a network call here
          observer.onNext("Hello " + name + "!");
          observer.onCompleted();
        }
      } catch (Exception e) {
        observer.onError(e);
      }
    }).subscribeOn(Schedulers.io());
  }
}
