package deepthinking.fgi.Enum;

public enum InOrOutType {
    in(0),out(1);

    private long type;

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    InOrOutType(long type) {
        this.type = type;
    }
}
