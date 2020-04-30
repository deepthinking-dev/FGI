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
public class InterfaceXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;    //接口名称

    private String desc;    //描述

    private String remark;  //备注

    private AlgorithmXmlModel algorithm;    //算子

    private InterfaceParamsXmlModel params; //接口参数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlgorithmXmlModel getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(AlgorithmXmlModel algorithm) {
        this.algorithm = algorithm;
    }

    public InterfaceParamsXmlModel getParams() {
        return params;
    }

    public void setParams(InterfaceParamsXmlModel params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
