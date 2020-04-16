package deepthinking.fgi.Enum;

/**
 * @author jagoLyu
 * @Description: 分组类型的枚举
 */
public enum GroupType {
    model(1),algorithm(2),role(3);

    private int type;

    GroupType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
