package com.shop.base.user.dao;

import com.shop.base.user.entity.BaseUserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleDao extends PagingAndSortingRepository<BaseUserRole, String> {
}
