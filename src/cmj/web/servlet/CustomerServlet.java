package cmj.web.servlet;

import cmj.domain.BaseDict;
import cmj.domain.Customer;
import cmj.domain.SysUser;
import cmj.util.NavigationTag;
import cmj.util.Page;
import cmj.web.service.BaseDictService;
import cmj.web.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller

public class CustomerServlet {

    @Autowired
    CustomerService customerService;

    @Autowired
    BaseDictService baseDictService;

    @RequestMapping("customer/list.action")
    public String selectALL(Model model,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer rows,
                            String custName, String custSource,
                            String custIndustry, String custLevel
    ) {

        Page<Customer> customerPage = customerService.selectALL(page, rows, custName, custSource, custIndustry, custLevel);
        System.out.println("当前页:" + page + ",每页多少条:" + rows + ",custName=" + custName + ",custSource=" +
                custSource + ",custIndustry=" + custIndustry + ",custLevel=" + custLevel);
        model.addAttribute("page", customerPage);
        List<BaseDict> fromType = baseDictService.selectDictTypeName("002");
        List<BaseDict> industryType = baseDictService.selectDictTypeName("001");
        List<BaseDict> levelType = baseDictService.selectDictTypeName("006");
        model.addAttribute("fromType", fromType);
        model.addAttribute("industryType", industryType);
        model.addAttribute("levelType", levelType);
        model.addAttribute("custName", custName);
        model.addAttribute("custSource", custSource);
        model.addAttribute("custIndustry", custIndustry);
        model.addAttribute("custLevel", custLevel);


        return "customer";
    }

    @RequestMapping("/customer/create.action")
    @ResponseBody
    public String createCustomer(HttpSession httpSession,
                                 Customer customer) {
        SysUser user_session = (SysUser) httpSession.getAttribute("USER_SESSION");
        //设置id
        customer.setCustCreateId(user_session.getUserId());
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setCustCreatetime(timestamp);
        System.out.println("赋值的阐述:+++"+customer);
        int rows = customerService.createCustomer(customer);
        if(rows>0){
            return "OK";
        }
        return "不会创建就不要创建，乱填什么，是个sb";
    }

    @RequestMapping("customer/getCustomerById.action")
    @ResponseBody
    public Customer AfterUpdateCustomer(@RequestParam("id")Integer id) {
        Customer customer = customerService.getCustomer(id);
        System.out.println("这封装的什么玩意:"+customer);
        return customer;
    }
    @RequestMapping("customer/update.action")
    @ResponseBody
    public String updateCustomer(Customer customer) {

        System.out.println("拿到的又是个什么玩意:"+customer);
        int rows = customerService.updateCustomer(customer);

        if(rows>0){
            return "OK";
        }
        return "不会更新就不要更新，更新什么，是个脑瘫";
    }

    @RequestMapping("customer/delete.action")
    @ResponseBody
    public String deleteCustomer(Integer id) {

        System.out.println("拿到的又是个什么玩意:"+id);
        int rows = customerService.deleteCustomer(id);
        if(rows>0){
            return "OK";
        }
        return "删除失败了";
    }



}
