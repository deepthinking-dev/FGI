package deepthinking.fgi.service.impl;

import deepthinking.fgi.dao.mapper.TableAlgorithmMapper;
import deepthinking.fgi.dao.mapper.TableModuleMapper;
import deepthinking.fgi.dao.mapper.TableRoleMapper;
import deepthinking.fgi.domain.TableAlgorithm;
import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableRole;
import deepthinking.fgi.service.ToOtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("toOtherService")
@Transactional
public class ToOtherServiceImpl implements ToOtherService {
    private static Logger logger = LoggerFactory.getLogger(ToOtherServiceImpl.class);

    @Resource
    private TableAlgorithmMapper tableAlgorithmMapper;
    @Resource
    private TableModuleMapper tableModuleMapper;
    @Resource
    private TableRoleMapper tableRoleMapper;

    @Value("${server.port}")
    private int serverPort;

    @Override
    public String findAlgorithmMessage(String type, int id) {
//        List<Map<String, Object>> result=new ArrayList<>();
        String url=null;
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        url="http://"+address.getHostAddress()+":"+this.serverPort+"/fgixq.html?id="+id+"&type="+type;
//        switch (type){
//            case "algorithm":
////                result=tableAlgorithmMapper.selectInfoByStatus(status);
//                url="http://"+address.getHostAddress()+":"+this.serverPort+"/fgixq.html?id="+id+"&type="+type;
//                break;
//            case "rule":
//                url="http://"+address.getHostAddress()+":"+this.serverPort;
////                result=tableRoleMapper.selectInfoByStatus(status);
//                break;
//            case "model":
//                url="http://"+address.getHostAddress()+":"+this.serverPort;
////                result=tableModuleMapper.selectInfoByStatus(status);
//        }
        return url;
    }

    @Override
    public boolean updateDataStatus(String type,int id, String status) {
        try {
            switch (type){
                case "algorithm":
                    TableAlgorithm tableAlgorithm=new TableAlgorithm();
                    tableAlgorithm.setId(id);
                    tableAlgorithm.setStatus(status);
                    tableAlgorithmMapper.updateByPrimaryKeySelective(tableAlgorithm);
                    break;
                case "rule":
                    TableRole tableRole=new TableRole();
                    tableRole.setStatus(status);
                    tableRole.setId(id);
                    tableRoleMapper.updateByPrimaryKeySelective(tableRole);
                    break;
                case "model":
                    TableModule tableModule=new TableModule();
                    tableModule.setStatus(status);
                    tableModule.setId(id);
                    tableModuleMapper.updateByPrimaryKeySelective(tableModule);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
