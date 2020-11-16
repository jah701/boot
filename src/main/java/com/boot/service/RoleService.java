package com.boot.service;

import com.boot.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleById(Long id);

    Role findByName(String roleName);
}
