package com.rayman.v2ex.ui.view.test;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestApiBinding;
import com.rayman.v2ex.presenter.ILifeCycle;
import com.rayman.v2ex.ui.view.base.BaseDIActivity;
import com.rayman.v2ex.viewmodel.test.TestApiVM;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class TestApiActivity extends BaseDIActivity {

    @Inject
    TestApiVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestApiBinding binding = bindLayout(R.layout.activity_test_api);
        binding.setViewModel(viewModel);
        Observable
                .create(subscriber1 -> {
                            Timber.i("Begin Main Thread %d", Thread.currentThread().getId());
                            Observable
                                    .create((subscriber) -> {
                                        Timber.i("First %d", Thread.currentThread().getId());
                                        subscriber.onNext("First");
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

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTestApiComp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }

}
