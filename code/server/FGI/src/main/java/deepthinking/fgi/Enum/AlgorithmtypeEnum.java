package deepthinking.fgi.Enum;

public enum AlgorithmtypeEnum {
    /**
     * 算法类型：引用（1）；算法公式（2）；逻辑条件（3）
     */
    quote(1),formula(2),logical(3);

    private int algorithmtype;

    AlgorithmtypeEnum(int algorithmtype) {
        this.algorithmtype = algorithmtype;
    }

    public int getAlgorithmtype() {
        return algorithmtype;
    }

    public void setAlgorithmtype(int algorithmtype) {
        this.algorithmtype = algorithmtype;
    }
}
