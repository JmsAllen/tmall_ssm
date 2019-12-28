package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs= categoryService.list();

        // 获取总数据量
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    /**
     * @param c                 用于接收提交图片的分类名称
     * @param uploadedImageFile 用于接收上传的图片
     * @param session           当前应用的路径
     * @return 提供访问的URL
     */
    @RequestMapping("admin_category_add")
    public String addCategory(Category c, UploadedImageFile uploadedImageFile, HttpSession session) throws IOException {
        categoryService.add(c);

        // 若项目结构标准，则会将图片存在target下的project\tmallssm\target\tmall-ssm\img\category文件夹中
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // 将上传文件保存到一个目录文件中
        uploadedImageFile.getImage().transferTo(file);

        // 确保图片的格式一定是jpg，而不仅仅是名字为jpg
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        categoryService.delete(id);

        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(Model model, int id) {
        Category category = categoryService.get(id);
        model.addAttribute("c", category);
        return "admin/editCategory";
    }

    /**
     * @param category          获取对象
     * @param session
     * @param uploadedImageFile 用于接受上传的图片
     * @return
     * @throws IOException
     */
    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(category);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            File path = new File(session.getServletContext().getRealPath("img/category"));// 定位存放分类图片的路径
            File file = new File(path, category.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
