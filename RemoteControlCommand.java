interface Command {
    void execute();
}

class TurnOnCommand implements Command {
    private Device device;
    public TurnOnCommand(Device device) { this.device = device; }
    public void execute() { device.turnOn(); }
}

class TurnOffCommand implements Command {
    private Device device;
    public TurnOffCommand(Device device) { this.device = device; }
    public void execute() { device.turnOff(); }
}

class VolumeUpCommand implements Command {
    private Device device;
    public VolumeUpCommand(Device device) { this.device = device; }
    public void execute() { device.volumeUp(); }
}

interface Device {
    void turnOn();
    void turnOff();
    void volumeUp();
}

class TVDevice implements Device {
    public void turnOn() { System.out.println("TV ON"); }
    public void turnOff() { System.out.println("TV OFF"); }
    public void volumeUp() { System.out.println("TV Volume Up"); }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
}
