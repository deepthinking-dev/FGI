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
public class AlgorithmXmlModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;    //算子名称

    private String author;  //作者

    private int ispub;      //是否是公共算子 0-不是 1-是

    private int type;   //算子类型 1-引用 2-算法公式 3-逻辑条件

    private String func;    //公式

    private String desc;    //描述

    private Integer userid; //用户ID

    private String remark;  //备注

    private String algorithmGroup;  //所属组

    private String status;  //状态

    private String remarks2;    //备注

    private ParamsXmlModel params;  //参数

    public AlgorithmXmlModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIspub() {
        return ispub;
    }

    public void setIspub(int ispub) {
        this.ispub = ispub;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParamsXmlModel getParams() {
        return params;
    }

    public void setParams(ParamsXmlModel params) {
        this.params = params;
    }

    public String getAlgorithmGroup() {
        return algorithmGroup;
    }

    public void setAlgorithmGroup(String algorithmGroup) {
        this.algorithmGroup = algorithmGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }
}
