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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    public List<Map<String, Object>> findAlgorithmMessage(String status, String type) {
        List<Map<String, Object>> result=new ArrayList<>();
        switch (type){
            case "algorithm":
                result=tableAlgorithmMapper.selectInfoByStatus(status);
                break;
            case "rule":
                result=tableRoleMapper.selectInfoByStatus(status);
                break;
            case "model":
                result=tableModuleMapper.selectInfoByStatus(status);
        }
        return result;
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
