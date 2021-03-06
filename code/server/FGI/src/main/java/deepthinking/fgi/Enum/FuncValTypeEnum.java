package deepthinking.fgi.Enum;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/2/23 12:19
 */
public enum  FuncValTypeEnum{
    /**
     * 1  字段
     * 2  常量
     * 3  算法
     */
    field(1),constant(2),algorithm(3);

    private int type;

    FuncValTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static String getTypeName(int type){
        FuncValTypeEnum[] typeEnums=FuncValTypeEnum.values();
        for(FuncValTypeEnum typeVal:typeEnums){
            if(typeVal.getType()==type){
                return typeVal.name();
            }
        }
        return null;
    }


}
