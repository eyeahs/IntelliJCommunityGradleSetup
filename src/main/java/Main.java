import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by hyunwoopark on 2016. 10. 4..
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        Observable.fromCallable(() -> {
            Thread thread = Thread.currentThread();
            System.out.println("Name - " + thread.getName() + ", Id - " + thread.getId());
            return new Object();
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                        System.out.println(e);
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext");
                        if (o == null) {
                            System.out.println("Object is null");
                        }
                    }
                });

        Thread.sleep(1000);
    }
}
