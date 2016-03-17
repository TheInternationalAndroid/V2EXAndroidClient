package com.rayman.v2ex.widget.eventbus;

import com.rayman.v2ex.widget.eventbus.event.BaseEvent;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/11/16
 * Time: 3:01 PM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class RxBus {

    private static volatile RxBus instance = new RxBus();

    private Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus instance() {
        RxBus rxBus = instance;
        if (rxBus == null) {
            synchronized (RxBus.class) {
                rxBus = instance;
                if (rxBus == null) {
                    rxBus = instance = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void post(Object event) {
        bus.onNext(event);
    }

    public <T extends BaseEvent> Observable<T> asObservable(final Class<T> aClass) {
        return bus.asObservable()
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object event) {
                        return aClass.isInstance(event);
                    }
                })
                .cast(aClass);
    }

    public Observable<BaseEvent> asObservable() {
        return bus.asObservable().cast(BaseEvent.class);
    }


    public boolean hasObservers() {
        return bus.hasObservers();
    }

}
