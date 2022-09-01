package yegor.gulido.co.JvierBot.listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventListener extends ListenerAdapter {

//    @Override
//    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
//        List<Member> members = event.getGuild().getMembers();
//
//        int onlineMembers = 0;
//        for (Member member : members) {
//            if (member.getOnlineStatus() == OnlineStatus.ONLINE) {
//                onlineMembers++;
//            }
//        }
//
//        User user = event.getUser();
//        String message = "Пользователь **" + user.getAsTag() + "**" + " обновил свой статус! Сейчас " + onlineMembers + " онлайн";
//        TextChannel textChannel = event.getGuild().getTextChannelsByName("general", false).get(0);
//        textChannel.sendMessage(message).queue();
//    }
}
