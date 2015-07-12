package async;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private AsyncService realAsyncService;

    @Value("${app.goodasync:false}")
    private boolean isGoodAsync;

    @Override
    public void run(String... args) throws Exception {
        Future<String> fooService;
        Future<String> barService;

        StopWatch swOverall = new StopWatch();
        swOverall.start("overall");

        StopWatch sw = new StopWatch();
        sw.start();

        if (isGoodAsync) {
            // call async methods through service call
            System.out.println("Starting up REAL async services.");
            fooService = this.realAsyncService.doFoo("an argument");
            barService = this.realAsyncService.doBar("another argument");
        } else {
            System.out.println("Starting up BROKEN async services.");
            fooService = this.doFoo("an argument");
            barService = this.doBar("another argument");
        }

        sw.stop();
        System.out.println(
                "Took " + sw.getTotalTimeMillis() + " ms to start threads.");

        sw.start();
        fooService.get();
        sw.stop();
        System.out.println("Foo done in " + sw.getTotalTimeMillis() + " ms.");

        sw.start();
        barService.get();
        sw.stop();
        System.out.println("Bar done in " + sw.getTotalTimeMillis() + " ms.");

        swOverall.stop();
        System.out.println("Total time ellapsed "
                + swOverall.getTotalTimeMillis() + " ms.");
    }

    @Async
    public Future<String> doFoo(String someArgument)
            throws InterruptedException {
        System.out.println("about to start Foo service (broken async)");
        Thread.sleep(3000);
        System.out.println("finishing Foo service (broken async)");

        return new AsyncResult<String>(
                "You finished a broken thread from Foo Service (broken async)");
    }

    @Async
    public Future<String> doBar(String someArgument)
            throws InterruptedException {
        System.out.println("about to start Bar service (broken async)");
        Thread.sleep(3000);
        System.out.println("finishing Bar service (broken async)");

        return new AsyncResult<String>(
                "You finished a broken thread from Bar Service (broken async)");
    }

}