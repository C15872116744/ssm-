package cmj.web.service;

import cmj.dao.BaseDictMapper;
import cmj.domain.BaseDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
//@Transactional
public class BaseDictService {


    @Autowired
    BaseDictMapper baseDictMapper;

    public List<BaseDict> selectDictTypeName(String id){
        return baseDictMapper.selectDictTypeName(id);
    }

}
