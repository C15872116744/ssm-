package cmj.web.service;

import cmj.dao.CustomerMapper;
import cmj.domain.Customer;
import cmj.util.Page;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
//@Transactional
public class CustomerService {


    @Autowired
    CustomerMapper customerMapper;


//    public List<Customer> getCustomers(Customer customer) {
//        return customerMapper.selectCustomerList(customer);
//    }
//
//    public Integer getCount(Customer customer) {
//        return customerMapper.selectCount(customer);
//    }

    public Page<Customer> selectALL(
                                    Integer start, Integer rows, String custName,
                                    String custSource, String custIndustry,
                                    String custLevel
    ){

        // custName  custSource  custIndustry  custLevel
        Customer customer = new Customer();
        //判断录入名称
        if(StringUtils.isNotBlank(custName)){
            customer.setCustName(custName);
        }
        //判断信息来源
        if(StringUtils.isNotBlank(custSource)){
            customer.setCustSource(custSource);
        }
        ////判断职业
        if(StringUtils.isNotBlank(custIndustry)){
            customer.setCustIndustry(custIndustry);
        }
        //判断 用户等级
        if(StringUtils.isNotBlank(custLevel)){
            customer.setCustLevel(custLevel);
        }

        customer.setStart((start-1)*rows);
        customer.setRows(rows);

        System.out.println("封装的参数:"+customer);
        //得到查询所有的用户
        List<Customer> customers = customerMapper.selectCustomerList(customer);
        //得到查询总条数
        Integer count = customerMapper.selectCount(customer);
        //页面类
        System.out.println("查询到的条数:"+count);
        System.out.println("查询到的列表:"+customers);
        Page<Customer> customerPage = new Page<>();
//        private int total;    // 总条数
////        private int page;     // 当前页
////        private int size;     // 每页数
////        private List<T> rows; // 结果集
        customerPage.setRows(customers);
        customerPage.setPage(start);
        customerPage.setTotal(count);
        customerPage.setSize(rows);




        return customerPage;
    }

    public int createCustomer(Customer customer) {
        return customerMapper.insert(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    public int updateCustomer(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    public int deleteCustomer(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }
}
