package cloud.frizio.demo;

import java.util.Iterator;
import java.util.stream.IntStream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class SimplePublisher implements Publisher<Integer> {
    
    private final Iterator<Integer> iterator;

    public SimplePublisher(int count) {
        this.iterator = IntStream.rangeClosed( 1, count ).iterator();
    }

    public void subscribe(Subscriber<? super Integer> subscriber) {
        iterator.forEachRemaining( subscriber::onNext );
        subscriber.onComplete();
    }
}