package deepthinking.fgi.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author p.ww
 * @Date 2020/4/10
 **/
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
public class InterfaceParamsXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<InterfaceParamXmlModel> param = new ArrayList<InterfaceParamXmlModel>();

    public List<InterfaceParamXmlModel> getParam() {
        return param;
    }

    public void setParam(List<InterfaceParamXmlModel> param) {
        this.param = param;
    }
}
