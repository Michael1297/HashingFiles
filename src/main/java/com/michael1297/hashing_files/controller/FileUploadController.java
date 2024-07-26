package com.michael1297.hashing_files.controller;

import com.michael1297.hashing_files.dto.FileHash;
import com.michael1297.hashing_files.dto.FileHashResult;
import com.michael1297.hashing_files.service.FileHashingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class FileUploadController {

    private final FileHashingService fileHashingService;

    /**
     * Загрузить страницу при входе на сайт
     */
    @GetMapping("/")
    public String uploadForm(Model model) {
        model.addAttribute("error", "");
        model.addAttribute("hash", new FileHash());
        return "uploadForm";
    }

    /**
     * Вычислить хеш файла и отобразить его на странице
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   Model model) {
        FileHashResult hash = fileHashingService.hashing(file);

        model.addAttribute("error", hash.isHashed() ? "File is already hashed" : "");
        model.addAttribute("hash", hash.getFileHash());

        return "uploadForm";
    }

    /**
     * Обработчик исключений
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("hash", new FileHash());
        return "uploadForm";
    }
}
