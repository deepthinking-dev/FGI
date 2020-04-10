package deepthinking.fgi.model;

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
public class InterfaceXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;    //接口名称

    private Integer algorithmid;    //算子ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }
}
