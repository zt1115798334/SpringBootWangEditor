package com.zt.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author zhangtong
 * Created by on 2017/11/28
 */
@Controller
@RequestMapping
public class MyController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Object imgUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = System.getProperty("user.dir") + File.separator + "image" + File.separator;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        JSONObject result = new JSONObject();
        result.put("errno", 0);
        try {
            file.transferTo(dest);
            JSONArray ja = new JSONArray();
            ja.put(filePath);
            result.put("data", ja);
        } catch(IllegalStateException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
