// Command pattern example
interface Command {
    void execute();
}
class Light {
    public void on() { System.out.println("Light is ON"); }
}
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}
