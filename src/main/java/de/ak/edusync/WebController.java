package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class WebController {
    @RequestMapping("/index")
    public String generateWelcomePage(){
        return "index";
    }

    @GetMapping("/app")
    public String generateMainPage(Model model) {
        model.addAttribute("dataObject", new DataModel());
        return "app";
    }

    @PostMapping("/sync")
    public String loginToNextCloud(@ModelAttribute DataModel dataObject) throws IOException {
//        System.out.println(dataObject.getFileUrl()+ dataObject.getNextCloudUrl()+ dataObject.getPassword()+ dataObject.getUsername());


        //login to nextcloud
        NextcloudConnector nextcloud = dataObject.loginToNextcloud();

        //get date time for versioning (just for testing)
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        URL fileURL = new URL(dataObject.getFileUrl());
        String localFilePath = "src/main/resources/" + dataObject.getFileName();
        String remoteFolderPath = dtf.format(now);

        //delete older versions in local repo if it exists
        if(Files.exists(Paths.get(localFilePath))) {
            Files.delete(Paths.get(localFilePath));
        }

        //download new file
        InputStream in = new URL(dataObject.getFileUrl()).openStream();
        Files.copy(in, Paths.get(localFilePath));

        //put a folder with date and upload file into that folder
        nextcloud.createFolder(remoteFolderPath);
     //   nextcloud.uploadFile(, remoteFolderPath+"/"+dataObject.getFileName());


        //forward to start page if everything went well
        return "bye";
    }






}
