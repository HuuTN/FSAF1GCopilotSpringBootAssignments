public class House {
    private boolean garden, pool, basement;
    public House(HouseBuilder builder) {
        this.garden = builder.garden;
        this.pool = builder.pool;
        this.basement = builder.basement;
    }
    @Override
    public String toString() {
        return "House [garden=" + garden + ", pool=" + pool + ", basement=" + basement + "]";
    }
}
