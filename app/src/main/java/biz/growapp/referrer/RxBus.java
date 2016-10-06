package biz.growapp.referrer;

import rx.Observable;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public final class RxBus<T> {
    private final Subject<T, T> subject;
    public RxBus() {
        this(ReplaySubject.<T>create().toSerialized());
    }
    public RxBus(Subject<T, T> subject) {
        this.subject = subject;
    }
    public <E extends T> void send(E event) {
        subject.onNext(event);
    }
    public Observable<T> observeEvents() {
        return subject;
    }
    public <E extends T> Observable<E> observeEvents(Class<E> eventClass) {
        return subject.ofType(eventClass);
    }
}
