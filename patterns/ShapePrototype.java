// Prototype pattern example
public abstract class Shape implements Cloneable {
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Circle extends Shape {}
class Square extends Shape {}
