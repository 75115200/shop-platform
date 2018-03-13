package com.shop.file.dao;

import com.shop.file.model.FileInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * 文件Dao
 */
public interface FileDao extends CrudRepository<FileInfo, String>{
    FileInfo queryFileInfoByPath(String path);

    FileInfo queryFileInfoById(String id);
}
