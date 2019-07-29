package cloud.frizio.demo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

//import org.reactivestreams.Processor;
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;

public class SimpleProcessor implements Processor<Integer, Integer> {
	
    private ConcurrentLinkedQueue<Integer> numbers = new ConcurrentLinkedQueue<Integer>();
	
    private AtomicBoolean completed = new AtomicBoolean( false );
	
    Subscriber<? super Integer> subscriber;
 
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println( this.getClass().getSimpleName() + ": I'm subscribed now" );
    }
 
    @Override
    public void onNext(Integer number) {
        numbers.add( number * 2 );
    }
 
    @Override
    public void onError(Throwable t) {
        subscriber.onError(t);
    }
 
    @Override
    public void onComplete() {
        completed.set( true );
        System.out.println( this.getClass().getSimpleName() + ": completed" );
    }
 
    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        while ( !completed.get() || !numbers.isEmpty() ) {
            if ( !numbers.isEmpty() ) {
                subscriber.onNext( numbers.poll() );
            } else {
                try {
                    Thread.sleep( 200 );
                } catch (InterruptedException e) {}
            }
        }
        subscriber.onComplete();
    }
}