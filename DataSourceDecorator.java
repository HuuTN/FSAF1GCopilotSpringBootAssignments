interface DataSource {
    void writeData(String data);
    String readData();
}

class FileDataSource implements DataSource {
    private StringBuilder storage = new StringBuilder();
    public void writeData(String data) {
        storage.append(data);
    }
    public String readData() {
        return storage.toString();
    }
}

abstract class DataSourceDecorator implements DataSource {
    protected DataSource wrappee;
    public DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }
    public void writeData(String data) {
        wrappee.writeData(data);
    }
    public String readData() {
        return wrappee.readData();
    }
}

class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) { super(source); }
    public void writeData(String data) {
        super.writeData("[encrypted]" + data);
    }
    public String readData() {
        return super.readData().replace("[encrypted]", "");
    }
}

class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) { super(source); }
    public void writeData(String data) {
        super.writeData("[compressed]" + data);
    }
    public String readData() {
        return super.readData().replace("[compressed]", "");
    }
}
