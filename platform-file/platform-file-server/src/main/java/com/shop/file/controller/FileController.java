package com.shop.file.controller;

import com.shop.common.base.BaseResult;
import com.shop.common.util.JsonUtil;
import com.shop.file.model.FileInfo;
import com.shop.file.service.FileService;
import com.shop.file.util.HttpFileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

/**
 * 文件控制层
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("#{fileConfig.savePath}")
    private String savePath;

    @Value("#{fileConfig.urlPrefix}")
    private String urlPrefix;

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     * @param file 文件
     * @param callback 回调地址
     * @return
     */
    @RequestMapping("/upload.json")
    @ResponseBody
    @CrossOrigin
    public BaseResult upload(@RequestParam("file") MultipartFile[] file, @RequestParam(required = true) String callback, HttpServletRequest request) {
        if (file == null) {
            return BaseResult.fail("缺乏上传文件");
        }

        String result = null;
        List<FileInfo> fileInfos = new ArrayList<>(file.length);
        try {
            for (MultipartFile f : file) {
                String fileName = renameToUUID(f.getOriginalFilename());
                String dirPath = LocalDate.now().toString() + "/";
                File targetPath = new File(savePath + dirPath);
                if (!targetPath.exists() && !targetPath.mkdir()) {
                    LOGGER.error("创建上传文件夹失败:" + targetPath);
                    return BaseResult.fail("上传失败，创建文件异常");
                }
                // 保存文件
                f.transferTo(new File(savePath + dirPath + fileName));
                FileInfo fileInfo = new FileInfo(f.getOriginalFilename(), dirPath + fileName);
                fileInfos.add(fileService.saveFileInfo(fileInfo));
            }

            if (CollectionUtils.isEmpty(fileInfos)) {
                return BaseResult.fail("上传文件失败,列表为空");
            }

            // 回调
            result = HttpFileUtil.callback(callback, request.getParameterMap(), fileInfos);
        } catch (IOException e) {
            LOGGER.error("上传文件失败");
            return BaseResult.fail("上传文件失败，IOE异常");
        }
        return BaseResult.success(result);
    }

    @RequestMapping(value = "/upload.json", method = RequestMethod.OPTIONS)
    @ResponseBody
    @CrossOrigin
    public BaseResult uploadOptions() {
        return BaseResult.success();
    }

    @RequestMapping("/download")
    public void download(String id, HttpServletResponse response){
        FileInfo fileInfo = fileService.queryFileInfoById(id);
        if (fileInfo != null) {
            FileInputStream fis= null;
            try {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileInfo.getName(), "ISO-8859-1"));
                File file=new File(savePath + fileInfo.getPath());
                fis = new FileInputStream(file);
                IOUtils.copy(fis, response.getOutputStream());
                response.setContentLengthLong(file.length());
            } catch (FileNotFoundException e) {
                LOGGER.error("文件不存在", e);
            } catch (IOException e) {
                LOGGER.error("读取文件错误", e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        LOGGER.error("关闭输入流异常：", e);
                    }
                }
            }
        }
    }

    /**
     * 将文件重命名为uuid格式
     * @param fileName 文件名
     * @return
     */
    private String renameToUUID(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException("文件格式不正确");
        }
        return UUID.randomUUID().toString() + fileName.substring(index);
    }

}
