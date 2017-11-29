package com.zt.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zt.entity.ImageInfo;
import com.zt.service.ImageInfoService;
import com.zt.utils.FileUploadUtil;
import com.zt.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author zhangtong
 * Created by on 2017/11/28
 */
@Controller
@RequestMapping
public class MyController {

    @Autowired
    private ImageInfoService imageInfoService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpload(HttpServletRequest request) {
        String filePath = System.getProperty("user.dir") + File.separator + "image" + File.separator;
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        List<UploadFile> files = fileUploadUtil.getFiles(request, filePath);
        JSONObject result = new JSONObject();
        result.put("errno", "0");
        files.stream().forEach(file -> {
            JSONArray ja = new JSONArray();
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setFileUrl(file.file.getPath());
            imageInfo.setFileSize(file.getFileSize());
            imageInfo.setFileMD5(file.getFileMD5());
            imageInfo.setFullFileName(file.getFullFileName());
            imageInfo.setOriginalFileName(file.getOriginalFileName());
            imageInfo.setFileName(file.getFileName());
            imageInfo.setSuffixName(file.getSuffixName());
            imageInfo = imageInfoService.save(imageInfo);
            ja.add("/show/" + imageInfo.getId());
            result.put("data", ja);
        });
        System.out.println("result = " + result.toJSONString());
        return result;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public void imgShow(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        try {
            ImageInfo imageInfo = imageInfoService.findById(id);
            String path = imageInfo.getFileUrl();
            File filePath = new File(path);
            if (filePath.exists()) {
                //读图片
                FileInputStream inputStream = new FileInputStream(filePath);
                int available = inputStream.available();
                byte[] data = new byte[available];
                inputStream.read(data);
                inputStream.close();
                //写图片
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
