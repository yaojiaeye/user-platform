package com.yaojia.projects.user.management;

import com.yaojia.projects.user.domain.User;

public interface UserManagerMBean {
    // MBeanAttributeInfo 列表
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    // MBeanOperationInfo
    @Override
    String toString();

    User getUser();
}
