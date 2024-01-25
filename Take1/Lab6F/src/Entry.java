public class Entry {
    private Type type;
    private String key;
    private String value;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
        this.type = Type.KV_PAIR;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    /**
     * Sets this Entry as deleted.
     */
    public void markTombstone() {
        // TODO since entries can only contain data or be marked an entry was deleted
        // Clear the data and change type to tombstone
        if(getType() == Type.KV_PAIR) {
            this.key = null;
            this.value = null;
            this.type = Type.TOMBSTONE;
        }
    }

    // Entries can either contain data, or mark that an entry was deleted
    public enum Type {
        KV_PAIR,
        TOMBSTONE
    }
}
