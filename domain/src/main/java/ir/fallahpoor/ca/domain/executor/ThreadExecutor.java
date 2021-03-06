package ir.fallahpoor.ca.domain.executor;

import java.util.concurrent.Executor;

import ir.fallahpoor.ca.domain.interactor.UseCase;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the {@link UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
