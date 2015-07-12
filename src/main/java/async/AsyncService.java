package async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public Future<String> doFoo(String someArgument)
            throws InterruptedException {
        System.out.println("about to start Foo service (good async)");
        Thread.sleep(3000);
        System.out.println("finishing Foo service (good async)");

        return new AsyncResult<String>(
                "Congrats. You finished a real thread from Foo Service (good async)");
    }

    @Async
    public Future<String> doBar(String someArgument)
            throws InterruptedException {
        System.out.println("about to start Bar service (good async)");
        Thread.sleep(3000);
        System.out.println("finishing Bar service (good async)");

        return new AsyncResult<String>(
                "Congrats. You finished a real thread from Bar Service (good async)");
    }
}
