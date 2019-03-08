package com.egdfrm.core.service.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.model.standard.TtApplicationUserCriteria;

@Service
public class TestUserService {

    @Autowired
    private TtApplicationUserMapper taum;

    /**
     * <p>
     * Description: 返回用户列表
     * </p>
     * 
     * @return 列表
     */
    public List<TtApplicationUser> getUserList() {
        return this.taum.selectByExample(new TtApplicationUserCriteria());
    }
}
