interface VendingState {
    void insertMoney(VendingMachine machine);
    void dispense(VendingMachine machine);
}

class IdleState implements VendingState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money inserted");
        machine.setState(new HasMoneyState());
    }
    public void dispense(VendingMachine machine) {
        System.out.println("Insert money first");
    }
}

class HasMoneyState implements VendingState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Already has money");
    }
    public void dispense(VendingMachine machine) {
        if (machine.getStock() > 0) {
            System.out.println("Dispensing item");
            machine.setStock(machine.getStock() - 1);
            if (machine.getStock() == 0) {
                machine.setState(new OutOfStockState());
            } else {
                machine.setState(new IdleState());
            }
        } else {
            machine.setState(new OutOfStockState());
            machine.getState().dispense(machine);
        }
    }
}

class OutOfStockState implements VendingState {
    public void insertMoney(VendingMachine machine) {
        System.out.println("Out of stock");
    }
    public void dispense(VendingMachine machine) {
        System.out.println("Cannot dispense, out of stock");
    }
}

class VendingMachine {
    private VendingState state;
    private int stock;
    public VendingMachine(int stock) {
        this.stock = stock;
        this.state = stock > 0 ? new IdleState() : new OutOfStockState();
    }
    public void setState(VendingState state) { this.state = state; }
    public VendingState getState() { return state; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public void insertMoney() { state.insertMoney(this); }
    public void dispense() { state.dispense(this); }
}
