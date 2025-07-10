public class HouseBuilder {
    boolean garden, pool, basement;
    public HouseBuilder garden() { garden = true; return this; }
    public HouseBuilder pool() { pool = true; return this; }
    public HouseBuilder basement() { basement = true; return this; }
    public House build() { return new House(this); }
}
