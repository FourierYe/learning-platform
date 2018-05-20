package com.hhit.learn.controller;

import java.io.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hhit.learn.entity.ArticleEntity;
import com.hhit.learn.service.ArticleService;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * The type Upload controller.
 *
 * @program: learn
 * @description: 用于上传文件的Controller
 * @author: GeekYe
 * @create: 2018 -04-19 23:28
 */
@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * Upload pic.
     *
     * @param request  the request
     * @param response the response
     * @param attach   the attach
     */
    @RequestMapping(value="/user/article/uploadPic", method= RequestMethod.POST)
    public void uploadPic(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){

        try {

            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );
            //I:\Projects\hhit\src\main\java\com\hhit\learn\controller\UploadController.java
            String rootPath = request.getSession().getServletContext().getRealPath("/resources/upload/");

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath=new File(rootPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }

            //最终文件名
            File realFile=new File(rootPath+File.separator+attach.getOriginalFilename());
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

            //下面response返回的json格式是editor.md所限制的，规范输出就OK
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/resources/upload/" + attach.getOriginalFilename() + "\"}" );
        } catch (Exception e) {
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Handle file upload string.
     *
     * @param request the request
     * @param model   the model
     * @return the string
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request, Model model) {

        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("USER_ID");

        ArticleEntity articleEntity = articleService.getArticleByUserTimeLimitOne(userId);

        String articleId = String.valueOf(articleEntity.getPkArticleId());

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("up_file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        File filePath = new File("/Files"+"/"+articleId);

        if(!filePath.exists()){
            filePath.mkdirs();
        }

        for (int i = 0; i < files.size(); ++i) {

            file = files.get(i);
            File file1 = new File(filePath.getPath()+File.separator+file.getOriginalFilename());

            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(file1));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    model.addAttribute("error", "服务器错误，请重试");
                    return "redirect:/user/forwardUploadFile";
                }
            } else {
                model.addAttribute("error", "文件不能为空，请重新添加");
                return "redirect:/user/forwardUploadFile";
            }
        }
        model.addAttribute("message", "附件添加成功，是否继续添加");
        return "redirect:/user/forwardUploadFile";
    }

    /**
     * Delete file string.
     *
     * @param filename    the filename
     * @param httpSession the http session
     * @return the string
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/user/deleteFile", method = RequestMethod.GET)
    public String deleteFile(@RequestParam(value = "filename") String filename, HttpSession httpSession){
        String articleId = (String) httpSession.getAttribute("articleId");

        File file = new File("/Files/"+articleId+"/"+filename);

        if (file.exists() && file.isFile()) {
            file.delete();
        }

        return "redirect:/user/forwardUploadFile";
    }

    /**
     * Download file.
     *
     * @param request   the request
     * @param response  the response
     * @param fileName  the file name
     * @param articleId the article id
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @Param(value = "fileName") String fileName, @Param(value = "articleId") String articleId) {

        if (fileName != null) {
            //设置文件路径
            File file = new File("/Files/"+articleId+"/"+fileName);
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
