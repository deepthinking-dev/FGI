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
public class BehavioursXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<BehaviourXmlModel> action = new ArrayList<BehaviourXmlModel>();

    public BehavioursXmlModel() {
    }

    public List<BehaviourXmlModel> getAction() {
        return action;
    }

    public void setAction(List<BehaviourXmlModel> action) {
        this.action = action;
    }
}
