package ir.fallahpoor.ca.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error
 * handling.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable exception) {
    }
}
