package deepthinking.fgi.util;


import deepthinking.fgi.model.InterfaceXmlModel;
import deepthinking.fgi.model.InterfacesXmlModel;
import deepthinking.fgi.model.RuleXmlModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 创建需要转换的对象
//        User2 user2 = new User2();
//        user2.setAge(5);
//        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);
//        user.setUser2(user2);

        RuleXmlModel rule = new RuleXmlModel();
        rule.setName("规则一");
        rule.setDesc("规则一描述");
        rule.setRemark("规则一备注");
        rule.setEntrancenote("规则一入口备注");
        rule.setCoordinate("规则一坐标");
        rule.setUserid(1001);


        InterfacesXmlModel interfaces = new InterfacesXmlModel();

        InterfaceXmlModel interface01 = new InterfaceXmlModel();
        interface01.setName("接口1");
        interface01.setAlgorithmid(9001);

        InterfaceXmlModel interface02 = new InterfaceXmlModel();
        interface02.setName("接口2");
        interface02.setAlgorithmid(9002);

        interfaces.getInterfa().add(interface01);
        interfaces.getInterfa().add(interface02);

        rule.setInterfaces(interfaces);

        String path = "E:\\user.xml";
        System.out.println("---将对象转换成File类型的xml Start---");
        XMLUtil.convertToXml(rule, path);
        System.out.println("---将对象转换成File类型的xml End---");
        System.out.println();
//        System.out.println("---将File类型的xml转换成对象 Start---");
//        User user2 = (User) XMLUtil.convertXmlFileToObject(User.class, path);
//        System.out.println(user2);
//        System.out.println("---将File类型的xml转换成对象 End---");
    }
}
