package com.shop.file.service;

import com.shop.file.model.FileInfo;

import java.io.File;
import java.util.List;

public interface FileService {

    /**
     * 保存文件信息
     * @param fileInfo
     * @return
     */
    FileInfo saveFileInfo(FileInfo fileInfo);

    /**
     * 通过相对路径查询文件信息
     * @param path 相对路径
     * @return
     */
    FileInfo queryFileInfoByPath(String path);

    /**
     * 通过文件id查询文件信息
     * @param id
     * @return
     */
    FileInfo queryFileInfoById(String id);
}
