package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.Result;
import cn.cuit.edu.achieve.service.ResultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

/**
 * 上传文件控制层
 *
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UpAndDownloadFile
 * @date 2021/6/3 12:23
 */
@CrossOrigin
@RestController
public class UpAndDownloadFile {
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Resource
    ResultService resultService;

    /**
     * 上传成果文件
     *
     * @param request    javax.servlet.http.HttpServletRequest
     * @param response   javax.servlet.http.HttpServletResponse
     * @param uploadFile org.springframework.web.multipart.MultipartFile
     * @return boolean
     * @method uploadResFile
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 12:23
     */
    @RequestMapping("/uploadResFile")
    @ResponseBody
    public boolean uploadResFile(HttpServletRequest request, HttpServletResponse response, MultipartFile uploadFile) throws Exception {
        boolean uploadSuccess = false;
        Integer resId = Integer.parseInt(request.getParameter("resId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String targetDirectory = staticAccessPath + userId + "/";
        // 先判断当前成果是否曾经上传过文件
        Result result = new Result();
        result.setResId(resId);
        List<Result> list = resultService.selectAll(result, null);
        result = list.get(0);
        String resFile = result.getResFile();
        // 如果该成果之前上传过成果文件，则先删除先前的，再上传新的
        if (!"".equals(resFile) && resFile != null) {
            File oldResFile = new File(targetDirectory + resFile);
            if (!(oldResFile.delete())) {
                throw new Exception("删除旧成果文件失败！");
            }
            result.setResFile(null);
            resultService.updateResult(result);
            System.out.println("已删除成果ID为" + resId + "的旧成果文件");
        }
        String fileName = uploadFile.getOriginalFilename();
        File dir = new File(targetDirectory, Objects.requireNonNull(fileName));
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("权限不足，无法创建文件夹");
            }
        }
        // MultipartFile自带的解析方法
        uploadFile.transferTo(dir);
        String uploadLocation = targetDirectory + fileName;
        System.out.println("文件已上传:" + uploadLocation);
        // 更新相应的成果信息
        result = new Result();
        result.setResId(resId);
        result.setResFile(fileName);
        if (resultService.updateResFile(result) == 1) {
            uploadSuccess = true;
        }
        return uploadSuccess;
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer resId = Integer.parseInt(request.getParameter("userId"));
        Result result = new Result();
        result.setResId(resId);
        List<Result> list = resultService.selectAll(result, null);
        result = list.get(0);
        String filename = result.getResFile();
        String filePath = staticAccessPath + result.getUserId() + "/" + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在:" + filePath);
        }
        // 转码，防止文件名中文乱码
        filename = URLEncoder.encode(filename, "UTF-8");
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            os.close();
        }
    }
}
