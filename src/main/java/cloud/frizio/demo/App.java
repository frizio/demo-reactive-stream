package cloud.frizio.demo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );
        System.out.println("java.version " + System.getProperty("java.version"));

        SimplePublisher publisher = new SimplePublisher(5);
        SimpleProcessor processor = new SimpleProcessor();
        SimpleSubscriber subscriber = new SimpleSubscriber();
            
        publisher.subscribe( processor );	
        processor.subscribe( subscriber );

    }
}
