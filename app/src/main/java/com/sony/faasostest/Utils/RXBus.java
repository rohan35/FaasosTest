package com.sony.faasostest.Utils;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Dell on 25-07-2018.
 */

public class RXBus {
    private static final BehaviorSubject<Object> behaviorSubject
            = BehaviorSubject.create();


    public static BehaviorSubject<Object> getSubject() {
        return behaviorSubject;
    }
    private static final BehaviorSubject<Object> behaviorSubject2
            = BehaviorSubject.create();


    public static BehaviorSubject<Object> getSubject2() {
        return behaviorSubject2;
    }

}
