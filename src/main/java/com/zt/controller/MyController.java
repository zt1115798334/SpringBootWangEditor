package com.zt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zt.utils.FileUploadUtil;
import com.zt.utils.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

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
    public Object imgUpload(HttpServletRequest request) {
        String filePath = System.getProperty("user.dir") + File.separator + "image" + File.separator;
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        List<UploadFile> files = fileUploadUtil.getFiles(request, filePath);
        JSONObject result = new JSONObject();
        result.put("errno","0");
        files.stream().forEach(file->{
            result.put("data", file.file.getPath());
        });
        return result;
    }

}
