interface Iterator<T> {
    boolean hasNext();
    T next();
}

class NameCollection {
    private String[] names;
    public NameCollection(String[] names) { this.names = names; }
    public NameIterator iterator() { return new NameIterator(); }
    private class NameIterator implements Iterator<String> {
        int index = 0;
        public boolean hasNext() { return index < names.length; }
        public String next() { return hasNext() ? names[index++] : null; }
    }
}
