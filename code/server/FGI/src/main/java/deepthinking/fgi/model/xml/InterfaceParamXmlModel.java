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
public class InterfaceParamXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;    //参数名称

    private Integer origin;  //参数来源

    private int iotype; //输入输出 0-输出 1-输入

    private String desc;    //描述

    private String remark;  //备注

    private BehavioursXmlModel actions; //动作

    private InterfaceParamXmlModel interfa;     //下一个接口

    public InterfaceParamXmlModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public int getIotype() {
        return iotype;
    }

    public void setIotype(int iotype) {
        this.iotype = iotype;
    }

    public BehavioursXmlModel getActions() {
        return actions;
    }

    public void setActions(BehavioursXmlModel actions) {
        this.actions = actions;
    }

    public InterfaceParamXmlModel getInterfa() {
        return interfa;
    }

    public void setInterfa(InterfaceParamXmlModel interfa) {
        this.interfa = interfa;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
