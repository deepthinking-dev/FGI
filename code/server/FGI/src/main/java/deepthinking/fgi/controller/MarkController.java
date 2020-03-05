package deepthinking.fgi.controller;

import deepthinking.fgi.util.Strs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/3/1 10:49
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("mark")
@Api(description = "05 公式参数识别API,算子维护及操作页面相关接口")
public class MarkController {

    private List<String> list_fun = new ArrayList<String>();

    public MarkController() {
        list_fun.add("sin");
        list_fun.add("cos");
        list_fun.add("tan");
        list_fun.add("sinh");
        list_fun.add("cosh");
        list_fun.add("tanh");
        list_fun.add("asin");
        list_fun.add("acos");
        list_fun.add("atan");
        list_fun.add("atan2");
        list_fun.add("deg");
        list_fun.add("rad");
        list_fun.add("abs");
        list_fun.add("round");
        list_fun.add("ceil");
        list_fun.add("floor");
        list_fun.add("exp");
        list_fun.add("ln");
        list_fun.add("log");
        list_fun.add("sqrt");
        list_fun.add("pow");
        list_fun.add("min");
        list_fun.add("max");
        list_fun.add("rnd");
        list_fun.add("sign");
        list_fun.add("if");
    }

    @GetMapping("getMark")
    @ApiOperation(notes = "获取变量值和运算符号", value = "id", httpMethod = "GET")
    @ApiImplicitParam(paramType = "query", dataType = "String", name = "target", value = "公式", required = true)
    public List<String> getMark(String target) {
        Set<String> xx = Strs.takeVar(target);
//        Set<String> yy = Strs.takeVar(regexMark, target);
        List<String> mark = new ArrayList<>();
        for(String x:xx){
            if(!list_fun.contains(x)){
                mark.add(x);
            }
        }
        return mark;
    }
}
