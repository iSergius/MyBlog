package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.FileMetadata;
import name.isergius.learn.myblog.domain.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show(@RequestParam(defaultValue = "1") Long page) {
        ModelAndView modelAndView = new ModelAndView();
        Page<FileMetadata> files = fileService.getFilesMetadata(10L);

        modelAndView.addObject("files", files);
        modelAndView.addObject("page", page);
        modelAndView.addObject("title","File Manager");
        modelAndView.setViewName("files");
        return modelAndView;
    }

    @RequestMapping(path = "{fileName}.{fileExtension}", method = RequestMethod.GET)
    public void download(@PathVariable("fileName") String fileName,
                         @PathVariable("fileExtension") String fileExtension,
                         HttpServletResponse response) {
        FileMetadata fileMetadata = fileService.getFileMetadataBy(fileName+"."+fileExtension);
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType(fileMetadata.getMimeType());
            outputStream.write(fileService.getFileBy(fileMetadata));
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("file-editor");
        FileMetadata fileMetadata = fileService.getFileMetadataBy(id);
        modelAndView.addObject("fileMetadata", fileMetadata);
        modelAndView.addObject("title",fileMetadata.getName());
        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/edit"}, method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public ModelAndView update(FileMetadata fileMetadata) {
        fileService.saveFileMetadata(fileMetadata);
        return new ModelAndView(new RedirectView("/file"));
    }

    @RequestMapping(path = {"/edit"}, method = RequestMethod.POST)
    public ModelAndView save(@RequestParam(value = "fileData") MultipartFile file) {
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setMimeType(file.getContentType());
        try {
            fileService.saveFile(fileMetadata, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title",fileMetadata.getName());
        modelAndView.setView(new RedirectView("/file/" + fileMetadata.getId() + "/edit"));
        return modelAndView;
    }

    @RequestMapping(path = {"/{id}/edit"}, method = RequestMethod.POST, consumes = "multipart/form-data")
    public ModelAndView updateFile(@PathVariable("id") Long id, @RequestParam(value = "fileData") MultipartFile file, HttpServletRequest request) {
        FileMetadata fileMetadata = fileService.getFileMetadataBy(id);
        try {
            fileService.saveFile(fileMetadata, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView(getReferer(request,"/file")));
    }

    @RequestMapping(path = {"{id}/delete"}, method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") Long id) {
        fileService.deleteBy(id);
        return new ModelAndView(new RedirectView("/file"));
    }

    private String getReferer(HttpServletRequest request, String url) {
        String referer = request.getHeader("Referer");
        if (referer != null) url = referer;
        return url;
    }

}
