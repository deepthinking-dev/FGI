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

    private String roleGroup;   //所属组

    private String status;      //状态

    private String remark2;     //备注2

    private String remark3;     //备注3

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

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }
}
