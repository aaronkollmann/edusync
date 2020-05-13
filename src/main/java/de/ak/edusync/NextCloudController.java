package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NextCloudController {

    @GetMapping("/nextcloud")
    public String generateNextCloudForm(Model model) {
        model.addAttribute("nextCloudAccount", new NextCloudAccountModel());
        return "nextcloud";
    }

    @PostMapping("/nextcloud-login")
    public String loginToNextCloud(@ModelAttribute NextCloudAccountModel nextCloudAccount) {
        NextcloudConnector nextCloud = new NextcloudConnector(nextCloudAccount.getUrl(), nextCloudAccount.getUsername(), nextCloudAccount.getPassword());
        return "index";
    }

}
