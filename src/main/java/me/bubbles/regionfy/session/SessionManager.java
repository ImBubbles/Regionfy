package me.bubbles.regionfy.session;

import me.bubbles.regionfy.regions.Region;
import me.bubbles.regionfy.regions.shape.WorldCuboid;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.stream.Collectors;

public class SessionManager {

    private final HashSet<Session> sessions;

    public SessionManager() {
        sessions=new HashSet<>();
    }

    public PlayerSession getPlayerSession(Player player) {
        PlayerSession result = null;
        for(Session session : sessions) {
            if(!(session instanceof PlayerSession)) {
                continue;
            }
            PlayerSession playerSession = (PlayerSession) session;
            if(playerSession.getBukkitPlayer().equals(player)) {
                result=playerSession;
                break;
            }
        }
        if(result==null) {
            PlayerSession playerSession = new PlayerSession(player);
            register(playerSession);
            result=playerSession;
        }
        return result;
    }

    public HashSet<Session> getByRegion(Region region) {
        return sessions.stream().filter(session -> session.inRegion(region)).collect(Collectors.toCollection(HashSet::new));
    }

    public void register(Session session) {
        if(sessions.contains(session)) {
            return;
        }
        sessions.add(session);
    }

    public void delete(Session Session) {
        sessions.remove(Session);
    }

}
