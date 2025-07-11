package com.example.demo.entity;

public enum OrderStatus {
// enum name and values
    PENDING,
    COMPLETED,
    CANCELED,
    SHIPPED,
    DELIVERED;
    // You can add methods or fields if needed
    @Override
    public String toString() {
        return name().toLowerCase(); // Converts enum name to lowercase for better readability
    }
    public static OrderStatus fromString(String status) {
        for (OrderStatus os : OrderStatus.values()) {
            if (os.name().equalsIgnoreCase(status)) {
                return os;
            }
        }
        throw new IllegalArgumentException("Unknown order status: " + status);
    }
    public boolean isFinalStatus() {
        return this == COMPLETED || this == CANCELED || this == DELIVERED;
    }
    public boolean isCancelable() {
        return this == PENDING;
    }
    public boolean isShippable() {
        return this == COMPLETED;
    }
    public boolean isDeliverable() {
        return this == SHIPPED;
    }
    public boolean isPending() {
        return this == PENDING;
    }
    public boolean isCompleted() {
        return this == COMPLETED;
    }
    public boolean isCanceled() {
        return this == CANCELED;
    }
    public boolean isShipped() {
        return this == SHIPPED;
    }
    public boolean isDelivered() {
        return this == DELIVERED;
    }
    public boolean isInProgress() {
        return this == PENDING || this == SHIPPED;
    }
    public boolean isNotFinal() {
        return this == PENDING || this == SHIPPED;
    }

    public boolean isActive() {
        return this == PENDING || this == SHIPPED || this == COMPLETED;
    }
    public boolean isInactive() {
        return this == CANCELED || this == DELIVERED;
    }
    public boolean isValidTransition(OrderStatus newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == COMPLETED || newStatus == CANCELED;
            case COMPLETED:
                return newStatus == SHIPPED || newStatus == CANCELED;
            case SHIPPED:
                return newStatus == DELIVERED || newStatus == CANCELED;
            case CANCELED:
            case DELIVERED:
                return false; // No further transitions allowed
            default:
                throw new IllegalArgumentException("Unknown order status: " + this);
        }
    }

    

}
