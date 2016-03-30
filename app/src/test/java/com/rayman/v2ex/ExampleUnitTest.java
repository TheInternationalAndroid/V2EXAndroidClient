package com.rayman.v2ex;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Observable
                .create(subscriber1 -> {
                            final long mainThread = Thread.currentThread().getId();
                            Timber.i("Begin Main Thread %d", mainThread);
                            Observable
                                    .create((subscriber) -> {
                                        Timber.i("First %d", Thread.currentThread().getId());
                                        subscriber.onNext("First");
                                        assert Thread.currentThread().getId() == mainThread;
                                    })
//                                    .doOnSubscribe(() -> Timber.i("doOnSubscribe %d", Thread.currentThread().getId()))
                                    .observeOn(Schedulers.newThread())
                                    .map((s) -> {
                                        Timber.i("Second %d", Thread.currentThread().getId());
                                        return s + " Second";
                                    })
                                    .subscribeOn(Schedulers.newThread())
                                    .map((s) -> {
                                        Timber.i("Third %d", Thread.currentThread().getId());
                                        return s + " Third";
                                    })
                                    .observeOn(Schedulers.newThread())
                                    .map((s) -> {
                                        Timber.i("Fourth %d", Thread.currentThread().getId());
                                        return s + " Fourth";
                                    })
                                    .observeOn(Schedulers.newThread())
                                    .subscribe(new Subscriber<String>() {
                                        @Override
                                        public void onStart() {
                                            super.onStart();
                                            Timber.i("onStart %d", Thread.currentThread().getId());
                                        }

                                        @Override
                                        public void onCompleted() {
                                            Timber.i("onCompleted %d", Thread.currentThread().getId());
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Timber.i("onError %d", Thread.currentThread().getId());
                                        }

                                        @Override
                                        public void onNext(String s) {
                                            Timber.i("onNext %d", Thread.currentThread().getId());
                                            Timber.i("onNext %s", s);
                                        }
                                    });
                        }
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe((s) -> Timber.i("End"));
    }
}