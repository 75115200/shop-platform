package com.shop.file.service;

import com.shop.file.dao.FileDao;
import com.shop.file.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("#{fileConfig.savePath}")
    private String savePath;

    @Value("#{fileConfig.urlPrefix}")
    private String urlPrefix;

    @Autowired
    private FileDao fileDao;

    @Transactional
    @Override
    public FileInfo saveFileInfo(FileInfo fileInfo) {
        return fileDao.save(fileInfo);
    }

    @Override
    public FileInfo queryFileInfoByPath(String path) {
        return fileDao.queryFileInfoByPath(path);
    }

    @Override
    public FileInfo queryFileInfoById(String id) {
        return fileDao.queryFileInfoById(id);
    }
}
