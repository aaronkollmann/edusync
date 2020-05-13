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
        System.out.println(discordAccount.getPassword());
        System.out.println(discordAccount.getUsername());
        //login with discord api
        return "index";
    }
}
