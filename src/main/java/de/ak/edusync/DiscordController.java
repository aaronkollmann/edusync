package de.ak.edusync;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscordController {

    @GetMapping("/discord")
    public String generateDiscordLoginForm(Model model){
        model.addAttribute("discordAccount", new DiscordAccountModel());
        return "discord";
    }

    @PostMapping("/discord-login")
    public String loginToDiscord(@ModelAttribute DiscordAccountModel discordAccount) {
        System.out.println("Logging in with token " + discordAccount.getToken());


        return "index";
    }
}
