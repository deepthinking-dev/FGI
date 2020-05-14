package deepthinking.fgi.controller;

import deepthinking.fgi.service.OtherclientService;
import deepthinking.fgi.service.ToOtherService;
import deepthinking.fgi.util.UserData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Api(description = "07 给第三发服务用的接口")
public class ToOtherController {
    @Resource
    private ToOtherService toOtherService;

    @GetMapping("/findAlgorithmMessage")
    @ApiOperation(value = "07-01 根据类别和ID获取数据详情信息", notes = "url地址", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "类别", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "id", value = "ID", dataType = "int", paramType = "query", required = true)})
    public String findAlgorithmMessage(String type, int id){
        return toOtherService.findAlgorithmMessage(type,id);
    }

    @GetMapping("/updateDataStatus")
    @ApiOperation(value = "07-02 修改数据的审批状态", notes = "修改数据的审批状态", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "类型", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "id", value = "数据ID", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态值", dataType = "string", paramType = "query", required = true)})
    public boolean updateDataStatus(String type,int id,String status){
        return toOtherService.updateDataStatus(type,id,status);
    }
    @PostMapping("/loginFGI")
    @ApiOperation(value = "07-03 同步登陆信息到FGI", notes = " 同步登陆信息到FGI", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string", paramType = "query", required = true)})
    public boolean loginFGI(String userName, String userId, HttpServletResponse response){
        try{
            Cookie cookie=new Cookie("fgi",userId);
            response.addCookie(cookie);
            UserData.saveUser(userName,userId,cookie);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @GetMapping("/logoutFGI")
    @ApiOperation(value = "07-04 退出FGI登陆", notes = " 同步登陆信息到FGI", httpMethod = "GET")
    @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string", paramType = "query", required = true)
    public boolean logoutFGI(String userId){
        try{
            UserData.removeUser(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("/loginforweb")
    @ApiOperation(value = "07-05 本系统前端同步cookie", notes = " 返回cookie信息", httpMethod = "GET")
    public boolean loginforweb(HttpServletRequest request, HttpServletResponse response){
        try{
            Cookie[] cookies = request.getCookies();
            boolean falg=true;
            if(cookies!=null&&cookies.length>0){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("fgi")){
                        falg=false;
                    }
                }
            }
            if(falg){
               Cookie cookie=new Cookie("fgi","fgiweb");
               response.addCookie(cookie);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

   @Autowired
    OtherclientService otherclientService;
    @GetMapping("/testOther")
    public String testOhterServer(){
        String str=otherclientService.getUser().toString();
        return str;
    }

}
