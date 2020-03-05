/********************************************
 * Druid连接池权限设置类
 *
 * @author zwq
 * @create 2018-06-02
 *********************************************/

package deepthinking.fgi.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * Druid的Servlet
 * @author Raye
 * @since 2016年10月7日14:13:39
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", 
initParams={
//        @WebInitParam(name="allow",value="localhost"),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name="loginUsername",value="admin"),// 用户名
        @WebInitParam(name="loginPassword",value="admin"),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {


}
