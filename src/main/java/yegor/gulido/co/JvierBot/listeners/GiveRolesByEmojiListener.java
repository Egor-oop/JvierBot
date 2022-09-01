package yegor.gulido.co.JvierBot.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GiveRolesByEmojiListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        long messageId = event.getMessageIdLong();
        Member member = event.getMember();
        String customerEmoji = event.getEmoji().getName();
        Role gamerRole = event.getGuild().getRoleById(1014907967918833765L);
        Role programmerRole = event.getGuild().getRoleById(1014908029126324284L);

        assert member != null;
        assert gamerRole != null;
        assert programmerRole != null;

        if (messageId == 1014920904607805510L) {
            if (customerEmoji.equals("\uD83C\uDFAE")) {
                event.getGuild().addRoleToMember(member, gamerRole).queue();
            } else if (customerEmoji.equals("\uD83D\uDCBB")) {
                event.getGuild().addRoleToMember(member, programmerRole).queue();
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        long messageId = event.getMessageIdLong();
        Member member = event.getMember();
        String customerEmoji = event.getEmoji().getName();
        Role gamerRole = event.getGuild().getRoleById(1014907967918833765L);
        Role programmerRole = event.getGuild().getRoleById(1014908029126324284L);

        assert member != null;
        assert gamerRole != null;
        assert programmerRole != null;

        if (messageId == 1014920904607805510L) {
            if (customerEmoji.equals("\uD83C\uDFAE")) {
                event.getGuild().removeRoleFromMember(member, gamerRole).queue();
            } else if (customerEmoji.equals("\uD83D\uDCBB")) {
                event.getGuild().removeRoleFromMember(member, programmerRole).queue();
            }
        }
    }
}
