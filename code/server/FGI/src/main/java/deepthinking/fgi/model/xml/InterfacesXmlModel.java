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
public class InterfacesXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<InterfaceXmlModel> interfa = new ArrayList<InterfaceXmlModel>(); //接口

    public List<InterfaceXmlModel> getInterfa() {
        return interfa;
    }

    public void setInterfa(List<InterfaceXmlModel> interfa) {
        this.interfa = interfa;
    }
}
