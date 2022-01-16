package com.example.graphQLDemo.util;

import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;

@RequiredArgsConstructor
public class CorrelationIdPropagationExecutor implements Executor {

  private final Executor executor;

  public static Executor wrap(Executor executor) {
    return new CorrelationIdPropagationExecutor(executor);
  }

  @Override
  public void execute(Runnable command) {
    String CORRELATION_ID = "correlation_id";
    var id = MDC.get(CORRELATION_ID);
    executor.execute(() ->
    {
      try{
        MDC.put(CORRELATION_ID, id);
        command.run();
      }
      finally {
        MDC.clear();;
      }
    });

  }

}
