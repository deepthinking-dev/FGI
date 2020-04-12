package deepthinking.fgi.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
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
public class ParamsXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ParamXmlModel> param = new ArrayList<ParamXmlModel>();

    public List<ParamXmlModel> getParam() {
        return param;
    }

    public void setParam(List<ParamXmlModel> param) {
        this.param = param;
    }
}
