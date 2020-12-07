package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.service.IService;

public interface IAppUserService extends IService<AppUser> {
    AppUser getUserByUsername(String name);
}
