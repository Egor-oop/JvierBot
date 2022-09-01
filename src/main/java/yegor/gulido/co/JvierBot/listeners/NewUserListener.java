package yegor.gulido.co.JvierBot.listeners;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class NewUserListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        // WILL NOT WORK WITHOUT GATEWAY INTENT!
        User user = event.getUser();
        String server = event.getGuild().getName();

        String message = user.getAsMention() + ", welcome to " + server + "!";

        TextChannel textChannel = event.getGuild().getTextChannelsByName("welcome", false).get(0);
        textChannel.sendMessage(message).queue();
    }
}
