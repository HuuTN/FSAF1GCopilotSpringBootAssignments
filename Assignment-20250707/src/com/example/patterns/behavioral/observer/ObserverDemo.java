package patterns.behavioral.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        Subscriber email = new EmailSubscriber("user@example.com");
        Subscriber sms = new SmsSubscriber("1234567890");
        agency.addSubscriber(email);
        agency.addSubscriber(sms);
        agency.publishNews("Breaking News: Observer Pattern Demo!");
        agency.removeSubscriber(email);
        agency.publishNews("Update: Only SMS will receive this.");
    }
}
