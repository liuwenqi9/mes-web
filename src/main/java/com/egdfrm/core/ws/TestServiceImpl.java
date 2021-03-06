package com.egdfrm.core.ws;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.service.ws.TestUserService;

@WebService
public class TestServiceImpl implements TestService {

    @Autowired
    private TestUserService tus;

    @Override
    public List<TtApplicationUser> getUserList() {
        return this.tus.getUserList();
    }

    @Override
    public String getName(String name) {
        return "hello " + name;
    }

}
