package deepthinking.fgi.service;

import java.util.List;
import java.util.Map;

/**
 * 给第三发服务
 * @author jagoLyu
 */
public interface ToOtherService {
    /**
     * 根据类别和ID获取数据详情信息
     * @param type
     * @param id
     * @return
     */
    String findAlgorithmMessage(String type, int id);

    /**
     * 修改数据的审批状态
     * @param id
     * @param status
     * @return
     */
    boolean updateDataStatus(String type,int id,String status);
}
