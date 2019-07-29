package cloud.frizio.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SimpleSubscriber implements Subscriber<Integer> {
 
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println( this.getClass().getSimpleName() + ": I'm subscribed now" );
    }
 
    @Override
    public void onNext(Integer t) {
        System.out.println( this.getClass().getSimpleName() + ": " +  t );
    }
 
    @Override
    public void onError(Throwable t) {
        System.err.println( t );
    }
 
    @Override
    public void onComplete() {
        System.out.println( this.getClass().getSimpleName() + ": completed" );
    }
}