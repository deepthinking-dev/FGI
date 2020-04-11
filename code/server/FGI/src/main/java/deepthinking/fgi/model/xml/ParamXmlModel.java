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
public class ParamXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;    //变量名称

    private String type;    //变量类型

    private String value;   //变量值

    private Integer iotype; //输入输出 0-输出 1-输入

    private String remark;  //备注

    public ParamXmlModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIotype() {
        return iotype;
    }

    public void setIotype(Integer iotype) {
        this.iotype = iotype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
