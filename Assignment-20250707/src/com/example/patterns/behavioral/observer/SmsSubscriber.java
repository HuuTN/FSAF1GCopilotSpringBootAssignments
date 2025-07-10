package patterns.behavioral.observer;

public class SmsSubscriber implements Subscriber {
    private String phoneNumber;

    public SmsSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String news) {
        System.out.println("SMS to " + phoneNumber + ": " + news);
    }
}
