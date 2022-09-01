package yegor.gulido.co.JvierBot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import yegor.gulido.co.JvierBot.commands.CommandManager;
import yegor.gulido.co.JvierBot.listeners.EventListener;
import yegor.gulido.co.JvierBot.listeners.GiveRolesByEmojiListener;
import yegor.gulido.co.JvierBot.listeners.NewUserListener;

import javax.security.auth.login.LoginException;

public class JvierBot {

    private final Dotenv config;

    private final ShardManager shardManager;

    public JvierBot() throws LoginException {
        // Load env variables
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        // Build shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("JvierBot"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

        // Register listeners
        shardManager.addEventListener(new CommandManager(), new GiveRolesByEmojiListener(), new NewUserListener());
    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            JvierBot bot = new JvierBot();
        } catch (LoginException e) {
            System.out.println("Error: The Bot token is INVALID!");
        }
    }
}
