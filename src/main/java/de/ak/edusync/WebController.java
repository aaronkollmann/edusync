package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebController {

    private NextcloudAccount nextcloudAccount;

    @RequestMapping(value = "/index")
    public String index() {

        return "index";
    }

    @GetMapping("/nextcloud-login")
    public String createNextcloudLoginForm(Model model) {
        model.addAttribute("nextcloudAccount", new NextcloudAccount());
        return "nextcloud-login";
    }

    @PostMapping("/submit-nextcloud-login")
    public String loginToNextcloud(@ModelAttribute NextcloudAccount nextcloudAccount) {
        NextcloudConnector nextcloud = new NextcloudConnector(nextcloudAccount.url, nextcloudAccount.username, nextcloudAccount.password);
        System.out.println("succesfully logged into nextcloud?");
        return "discord-login";
    }
}