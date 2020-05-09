package deepthinking.fgi.service;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调用其他服务接口
 * @author jagoLvy
 * otherclient
 */
@Service
//@FeignClient(url = "192.168.0.11",name = "otherclient")
public interface OtherclientService {
    /**
     * 获取用户数据  目前测试
     * @return
     */
    @RequestMapping(value = "/module/findAllTableFromDB",method = RequestMethod.GET)
    Object getUser();
}
