package com.how2java.tmall.controller;

import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.utils.ImageUtil;
import com.how2java.tmall.utils.Page;
import com.how2java.tmall.utils.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Allen
 */
@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        List<Category> cs = categoryService.list(page);
        int total = categoryService.total();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    /**
     *
     * @param c 用于接收提交图片的分类名称
     * @param uploadedImageFile 用于接收上传的图片
     * @param session 后续用于当前应用的路径
     * @return 提供访问的URL
     */
    @RequestMapping("admin_category_add")
    public String addCategory(Category c, UploadedImageFile uploadedImageFile, HttpSession session) throws IOException {
        categoryService.add(c);

        // 若项目结构标准，则会将图片存在target下的project\tmallssm\target\tmall-ssm\img\category文件夹中
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId()+".jpg");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        uploadedImageFile.getImage().transferTo(file);

        // 确保图片的格式一定是jpg，而不仅仅是名字为jpg
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

}
