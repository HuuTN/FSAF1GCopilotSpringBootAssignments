interface Notification {
    void notifyUser(String message);
}

class EmailNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("SMS: " + message);
    }
}

class PushNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("Push: " + message);
    }
}

class NotificationFactory {
    public static Notification createNotification(String type) {
        switch (type.toLowerCase()) {
            case "email": return new EmailNotification();
            case "sms": return new SMSNotification();
            case "push": return new PushNotification();
            default: throw new IllegalArgumentException("Unknown notification type");
        }
    }
}
