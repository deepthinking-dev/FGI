package deepthinking.fgi.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Description
 * @Author p.ww
 * @Date 2020/4/10
 **/
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "rule")
public class RuleXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;    //规则名称

    private String desc;    //规则描述

    private String remark;  //备注

    private String entrancenote;    //入口备注

    private String coordinate;  //坐标

    private Integer userid;     //用户ID

    private InterfacesXmlModel interfaces;  //接口

    public RuleXmlModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEntrancenote() {
        return entrancenote;
    }

    public void setEntrancenote(String entrancenote) {
        this.entrancenote = entrancenote;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public InterfacesXmlModel getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(InterfacesXmlModel interfaces) {
        this.interfaces = interfaces;
    }

}
