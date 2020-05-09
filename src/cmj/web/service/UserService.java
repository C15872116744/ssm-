package cmj.web.service;

import cmj.dao.SysUserMapper;
import cmj.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * @author cmj
 * @create 2020-04-26 15:45
 */
@Service
//@Transactional
public class UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    public SysUser login(String usercode, String password){

        SysUser sysUser = sysUserMapper.selectByIdAndPassword(usercode, password);

        return sysUser;
    }
}
