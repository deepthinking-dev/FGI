package deepthinking.fgi.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @Description
 * @Author p.ww
 * @Date 2020/4/10
 **/
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
public class BehaviourXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String behave;  //行为

    private String expression;  //表达式

    private Integer origin;      //条件值来源

    private String remark;      //备注

    public BehaviourXmlModel() {
    }

    public String getBehave() {
        return behave;
    }

    public void setBehave(String behave) {
        this.behave = behave;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
