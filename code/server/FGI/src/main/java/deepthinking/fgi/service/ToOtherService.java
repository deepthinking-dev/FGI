package deepthinking.fgi.service;

import java.util.List;
import java.util.Map;

/**
 * 给第三发服务
 * @author jagoLyu
 */
public interface ToOtherService {
    /**
     * 根据类别和状态获取数据信息，返回的map信息包含：ID，名称，描述，url等
     * @param status
     * @param type
     * @return
     */
    List<Map<String,Object>> findAlgorithmMessage(String status, String type);

    /**
     * 修改数据的审批状态
     * @param id
     * @param status
     * @return
     */
    boolean updateDataStatus(String type,int id,String status);
}
