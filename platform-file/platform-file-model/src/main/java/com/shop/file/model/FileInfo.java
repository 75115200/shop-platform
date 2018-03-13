package com.shop.file.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 文件
 */
@Entity
@Table(name = "file_info")
public class FileInfo {
    /**
     * 文件id
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * 文件名称
     */
    @Column
    private String name;

    /**
     * 文件相对路径
     */
    @Column
    private String path;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    @CreationTimestamp
    private Date uploadTime;

    public FileInfo() {
    }

    public FileInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
