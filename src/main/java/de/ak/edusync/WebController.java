package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public String startSyncJob(@ModelAttribute DataModel dataObject) throws IOException {

        //start background thread...
        Runnable r = new Runnable() {
            public void run() {
                while(true) {
                    try {
                        sync(dataObject.loginToNextcloud(), dataObject.getFileUrl(), dataObject.getRemoteFolderPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        //wait specified time
                        Thread.sleep(dataObject.getSyncInterval()*60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(r);

        //forward to start page if everything went well
        return "bye";
    }

    public void sync(NextcloudConnector nextcloud, String fileURL, String remoteFolderPath) throws IOException {

        //aux array to get filename from link
        String[] linkParts = fileURL.split("/");

        //save filename in var
        String fileName = linkParts[linkParts.length-1];

        //generate local path
        String localFilePath = fileName;

        //generate remote path
        String remoteFilePath = "/" + remoteFolderPath + "/" + fileName;

        //delete old files
        if(Files.exists(Paths.get(localFilePath))) {
            Files.delete(Paths.get(localFilePath));
        }

        //download latest file
        InputStream in = new URL(fileURL).openStream();
        Files.copy(in, Paths.get(localFilePath));


        //generate a Fileinputstream from that file
        InputStream nextcloudStream = new FileInputStream(localFilePath);


        //create desired folder if it doesnt exist yet
        if(!nextcloud.folderExists("/" + remoteFolderPath)) {
            nextcloud.createFolder("/" + remoteFolderPath);
        }

        //upload the file to the folder and replace
        if(nextcloud.fileExists(remoteFilePath)){
            nextcloud.removeFile(remoteFilePath);
        }
        //proof that input stream isnt empty
        System.out.println("Size of Inputstream: " + nextcloudStream.available());
        nextcloud.uploadFile(nextcloudStream, remoteFilePath);

    }


}
