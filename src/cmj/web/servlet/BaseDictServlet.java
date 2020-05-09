package cmj.web.servlet;

import cmj.domain.BaseDict;
import cmj.web.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BaseDictServlet {

//    @Autowired
//    BaseDictService baseDictService;


    @RequestMapping("baseDictService.action")
    public String select(){
//        List<BaseDict> fromType = baseDictService.selectDictTypeName("001");
//        List<BaseDict> industryType = baseDictService.selectDictTypeName("002");
//        List<BaseDict> levelType = baseDictService.selectDictTypeName("005");
//        model.addAttribute("fromType",fromType);
//        model.addAttribute("industryType",industryType);
//        model.addAttribute("levelType",levelType);

        return "customer";
    }
}
