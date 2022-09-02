package yegor.gulido.co.JvierBot.commands;

import net.dv8tion.jda.api.entities.*;
//import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        switch (command) {
            case "welcome" -> {
                // Run the '/welcome' command
                String userTag = event.getUser().getAsMention();
                event.reply("Welcome to the server, " + userTag + "!").setEphemeral(true).queue();
            }
            case "roles" -> {
                // Run the '/roles' command
                event.deferReply().setEphemeral(true).queue();
                String response = "";
                for (Role role : event.getGuild().getRoles()) {
                    response += role.getAsMention() + "\n";
                }
                event.getHook().sendMessage(response).queue();
            }
            case "echo" -> {
                OptionMapping messageOption = event.getOption("message");
                String message = "";
                if (messageOption != null) {
                    message = messageOption.getAsString();
                }
                MessageChannel channel;
                OptionMapping channelOption = event.getOption("channel");
                if (channelOption != null) {
                    channel = channelOption.getAsChannel().asTextChannel();
                } else {
                    channel = event.getChannel();
                }
                channel.sendMessage(message).queue();
                event.reply("Ваше сообщение было отправлено").setEphemeral(true).queue();
            }
            case "giverole" -> {
                Member member = event.getOption("user").getAsMember();
                Role role = event.getOption("role").getAsRole();
                Member messageAuthor = event.getMember();

                assert member != null;
                assert messageAuthor != null;

                List<Role> memberRoles = member.getRoles();

                String authorRole;
                try {
                    authorRole = messageAuthor.getRoles().get(0).getName();
                } catch (IndexOutOfBoundsException e) {
                    authorRole = "";
                }

                if (authorRole.equals("Admin")) {
                    boolean hasMemberRole = false;
                    for (Role memberRole : memberRoles) {
                        if (memberRole.equals(role)) {
                            hasMemberRole = true;
                            event.reply("This member already has the " + role.getAsMention() + " role").setEphemeral(true).queue();
                        }
                    }

                    if (!hasMemberRole) {
                        Objects.requireNonNull(event.getGuild()).addRoleToMember(member, role).queue();
                        event.reply(member.getAsMention() + " got the " + role.getAsMention() + " role").setEphemeral(true).queue();
                    }
                } else {
                    event.reply("You cannot give roles to members!").setEphemeral(true).queue();
                }
            }
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "The bot welcome you"));
        commandData.add(Commands.slash("roles", "Shows all roles on the server"));

        // Command: /echo <message>
        OptionData option1 = new OptionData(OptionType.STRING, "message", "Message that bot will say", true);
        OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "Channel that message will be sent", false)
                .setChannelTypes(ChannelType.TEXT, ChannelType.NEWS, ChannelType.GUILD_PUBLIC_THREAD);
        commandData.add(Commands.slash("echo", "Make bot say you want").addOptions(option1, option2));

        // Command: /giverole <user> <role>
        OptionData option3 = new OptionData(OptionType.USER, "user", "Who will have a role", true);
        OptionData option4 = new OptionData(OptionType.ROLE, "role", "The role user will have", true);
        commandData.add(Commands.slash("giverole", "Gives a role").addOptions(option3, option4));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

//    @Override
//    public void onReady(@NotNull ReadyEvent event) {
//        List<CommandData> commandData = new ArrayList<>();
//        commandData.add(Commands.slash("welcome", "Бот начинает приветствовать"));
//        commandData.add(Commands.slash("roles", "Показывает все роли сервера"));
//        event.getJDA().updateCommands().addCommands(commandData).queue();
//    }
}
