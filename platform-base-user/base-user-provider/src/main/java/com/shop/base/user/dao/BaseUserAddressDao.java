package com.shop.base.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shop.base.user.entity.BaseUserAddress;

public interface BaseUserAddressDao extends CrudRepository<BaseUserAddress, String> {
    
    @Query(value = "select a from BaseUserAddress a where user.id = ?1")
    List<BaseUserAddress> queryByUser(String userId);
}
