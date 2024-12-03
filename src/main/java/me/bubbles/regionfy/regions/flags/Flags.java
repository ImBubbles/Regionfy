package me.bubbles.regionfy.regions.flags;

import me.bubbles.regionfy.Regionfy;
import me.bubbles.regionfy.regions.Region;
import me.bubbles.regionfy.session.PlayerSession;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public enum Flags {

    PREVENT_BUILDING(
            new Flag(Arrays.asList(
                    BlockPlaceEvent.class,
                    BlockBreakEvent.class
            ), new FlagRunnable() {
                @Override
                public void run(Event event, Region region) {
                    Player player;
                    if(event instanceof BlockBreakEvent) {
                        BlockBreakEvent e = (BlockBreakEvent) event;
                        player=e.getPlayer();
                        Block clickedBlock = e.getBlock();
                        if(!region.isInside(clickedBlock.getState().getLocation())) {
                            return;
                        }
                        PlayerSession playerSession = Regionfy.getInstance().getSessionManager().getPlayerSession(player);
                        if(!playerSession.affectedByFlags()) {
                            return;
                        }
                        e.setCancelled(true);
                    }
                    if(event instanceof BlockPlaceEvent) {
                        BlockPlaceEvent e = (BlockPlaceEvent) event;
                        player=e.getPlayer();
                        Block clickedBlock = e.getBlock();
                        if(!region.isInside(clickedBlock.getState().getLocation())) {
                            return;
                        }
                        PlayerSession playerSession = Regionfy.getInstance().getSessionManager().getPlayerSession(player);
                        if(!playerSession.affectedByFlags()) {
                            return;
                        }
                        e.setCancelled(true);
                    }
                }
            })),
    PREVENT_INTERACT(
            new Flag(PlayerInteractEvent.class,
                    new FlagRunnable() {
                @Override
                public void run(Event event, Region region) {
                    Player player;
                    PlayerInteractEvent e = (PlayerInteractEvent) event;
                    player=e.getPlayer();
                    if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        return;
                    }
                    Block clickedBlock = e.getClickedBlock();
                    if(clickedBlock==null) {
                        return;
                    }
                    BlockData blockData = clickedBlock.getState().getBlockData();
                    /*if(!(blockData instanceof Openable)) {
                        return;
                    }*/
                    if(!region.isInside(clickedBlock.getLocation())) {
                        return;
                    }
                    PlayerSession playerSession = Regionfy.getInstance().getSessionManager().getPlayerSession(player);
                    if(!playerSession.affectedByFlags()) {
                        return;
                    }
                    e.setCancelled(true);
                }
            })),
    PREVENT_OPENABLE(
            new Flag(PlayerInteractEvent.class,
                    new FlagRunnable() {
                        @Override
                        public void run(Event event, Region region) {
                            Player player;
                            PlayerInteractEvent e = (PlayerInteractEvent) event;
                            player=e.getPlayer();
                            if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                return;
                            }
                            Block clickedBlock = e.getClickedBlock();
                            if(clickedBlock==null) {
                                return;
                            }
                            /*if(!(clickedBlock instanceof Chest||clickedBlock instanceof Barrel||clickedBlock instanceof EnderChest)) {
                                return;
                            }*/
                            if(!(clickedBlock.getType().equals(Material.CHEST)
                                    ||clickedBlock.getType().equals(Material.TRAPPED_CHEST)
                                    ||clickedBlock.getType().equals(Material.BARREL)
                                    ||clickedBlock.getType().equals(Material.ENDER_CHEST)
                                    ||clickedBlock.getType().equals(Material.GRINDSTONE)
                                    ||clickedBlock.getType().equals(Material.ENCHANTING_TABLE)
                                    ||clickedBlock.getType().equals(Material.CARTOGRAPHY_TABLE)
                                    ||clickedBlock.getType().equals(Material.FLETCHING_TABLE)
                                    ||clickedBlock.getType().equals(Material.SMITHING_TABLE)
                                    ||clickedBlock.getType().equals(Material.CRAFTING_TABLE))) {
                                return;
                            }
                            if(!region.isInside(clickedBlock.getLocation())) {
                                return;
                            }
                            PlayerSession playerSession = Regionfy.getInstance().getSessionManager().getPlayerSession(player);
                            if(!playerSession.affectedByFlags()) {
                                return;
                            }
                            e.setCancelled(true);
                        }
                    })),
    PREVENT_FALL_DAMAGE(
            new Flag(EntityDamageEvent.class,
                    new FlagRunnable() {
                        @Override
                        public void run(Event event, Region region) {
                            Player player;
                            EntityDamageEvent e = (EntityDamageEvent) event;
                            if(!(e.getEntity() instanceof Player)) {
                                return;
                            }
                            if(!e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                                return;
                            }
                            player=(Player) e.getEntity();
                            PlayerSession playerSession = Regionfy.getInstance().getSessionManager().getPlayerSession(player);
                            if(!region.isFlaggable(playerSession)) {
                                return;
                            }
                            e.setCancelled(true);
                        }
                    })),
    PREVENT_PVP(
            new Flag(EntityDamageByEntityEvent.class,
                    new FlagRunnable() {
                        @Override
                        public void run(Event event, Region region) {
                            region.getFlaggable();
                            Player player;
                            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                            if(!(e.getDamager() instanceof Player)) {
                                return;
                            }
                            player=(Player) e.getDamager();
                            if(!(e.getEntity() instanceof Player)) {
                                return;
                            }
                            if(!region.isFlaggable(player)) {
                                return;
                            }
                            e.setCancelled(true);
                        }
                    }));

    private final Flag flag;
    Flags(Flag flag) {
        this.flag=flag;
    }

    public Flag getFlag() {
        return flag;
    }

}
