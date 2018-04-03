package com.hhit.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @program: learn
 * @description: 用于文件上传测试
 * @author: GeekYe
 * @create: 2018-04-02 21:23
 **/
@RestController
public class TestController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String test(HttpServletRequest request){

        File dir = new File("src/main/resources/static/file");
        System.out.println(dir.getAbsolutePath());
        dir.mkdir();
        File file = new File("src/main/resources/static/file", "test文件");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        I:\Projects\hhit\src\main\resources\static
//        File file1=new File("I:\\Projects\\hhit\\src\\main\\resources\\static\\file");
//        if(file1.isDirectory()) {
//            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++是目录");
//        }else {
//            System.out.println("++++++++++++++++++++++++++++++++不是目录");
//        }
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
//                    stream.write(bytes);
//                    stream.close();
//                } catch (Exception e) {
//                    stream = null;
//                    return "You failed to upload " + i + " => " + e.getMessage();
//                }
//            } else {
//                return "You failed to upload " + i + " because the file was empty.";
//            }
//        }
//        return "upload successful";
        return "123";
    }
}
