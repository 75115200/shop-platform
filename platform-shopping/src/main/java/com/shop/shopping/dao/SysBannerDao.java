package com.shop.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.shopping.entity.SysBanner;

public interface SysBannerDao extends JpaRepository<SysBanner, String> {
    List<SysBanner> findAllByOrderBySeqAsc();
}
