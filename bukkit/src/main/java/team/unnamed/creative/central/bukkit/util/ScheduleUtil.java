/*
 * This file is part of creative-central, licensed under the MIT license
 *
 * Copyright (c) 2021-2023 Unnamed Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.unnamed.creative.central.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

// Original author: NCT-skyouo, NatureRevive
public class ScheduleUtil {
    private static boolean isFolia;
    static {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            isFolia = true;
        } catch (Exception ignored) {
            isFolia = false;
        }
    }

    public static class GLOBAL {
        public static void runTask(Plugin plugin, Runnable task) {
            if (isFolia) {
                Bukkit.getGlobalRegionScheduler().run(plugin, (ignored) -> task.run());
            } else {
                Bukkit.getScheduler().runTask(plugin, task);
            }
        }

        public static void runTaskLater(Plugin plugin, Runnable task, long delay) {
            if (isFolia) {
                Bukkit.getGlobalRegionScheduler().runDelayed(plugin, (ignored) -> task.run(), delay);
            } else {
                Bukkit.getScheduler().runTaskLater(plugin, task, delay);
            }
        }

        public static void runTaskTimer(Plugin plugin, Runnable task, long delay, long fixedRate) {
            if (isFolia) {
                Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, (ignored) -> task.run(), delay, fixedRate);
            } else {
                Bukkit.getScheduler().runTaskTimer(plugin, task, delay, fixedRate);
            }
        }

        public static void runTaskAsynchronously(Plugin plugin, Runnable task) {
            if (isFolia) {
                Bukkit.getAsyncScheduler().runNow(plugin, (ignored) -> task.run());
            } else {
                Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
            }
        }

        public static void runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) {
            if (isFolia) {
                Bukkit.getAsyncScheduler().runDelayed(plugin, (ignored) -> task.run(), delay * 50L, TimeUnit.MILLISECONDS);
            } else {
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay);
            }
        }

        public static void runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long fixedRate) {
            if (isFolia) {
                Bukkit.getAsyncScheduler().runAtFixedRate(plugin, (ignored) -> task.run(), delay * 50L, fixedRate * 50L, TimeUnit.MILLISECONDS);
            } else {
                Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, fixedRate);
            }
        }
    }

    public static class REGION {
        public static void runTask(Plugin plugin, Chunk chunk, Runnable task) {
            if (isFolia) {
                Bukkit.getRegionScheduler().run(plugin, chunk.getWorld(), chunk.getX(), chunk.getZ(), (ignored) -> task.run());
            } else {
                Bukkit.getScheduler().runTask(plugin, task);
            }
        }

        public static void runTask(Plugin plugin, Location location, Runnable task) {
            if (isFolia) {
                Bukkit.getRegionScheduler().run(plugin, location, (ignored) -> task.run());
            } else {
                Bukkit.getScheduler().runTask(plugin, task);
            }
        }

        public static void runTaskLater(Plugin plugin, Chunk chunk, Runnable task, long delay) {
            if (isFolia) {
                Bukkit.getRegionScheduler().runDelayed(plugin, chunk.getWorld(), chunk.getX(), chunk.getZ(), (ignored) -> task.run(), delay);
            } else {
                Bukkit.getScheduler().runTaskLater(plugin, task, delay);
            }
        }

        public static void runTaskLater(Plugin plugin, Location location, Runnable task, long delay) {
            if (isFolia) {
                Bukkit.getRegionScheduler().runDelayed(plugin, location, (ignored) -> task.run(), delay);

            } else {
                Bukkit.getScheduler().runTaskLater(plugin, task, delay);
            }
        }

        public static void runTaskTimer(Plugin plugin, Chunk chunk, Runnable task, long delay, long fixedRate) {
            if (isFolia) {
                Bukkit.getRegionScheduler().runAtFixedRate(plugin, chunk.getWorld(), chunk.getX(), chunk.getZ(), (ignored) -> task.run(), delay, fixedRate);
            } else {
                Bukkit.getScheduler().runTaskTimer(plugin, task, delay, fixedRate);
            }
        }

        public static void runTaskTimer(Plugin plugin, Location location, Runnable task, long delay, long fixedRate) {
            if (isFolia) {
                Bukkit.getRegionScheduler().runAtFixedRate(plugin, location, (ignored) -> task.run(), delay, fixedRate);
            } else {
                Bukkit.getScheduler().runTaskTimer(plugin, task, delay, fixedRate);
            }
        }
    }

    public static class ENTITY {
        public static void runTask(Plugin plugin, Entity entity, Runnable task) {
            if (isFolia) {
                entity.getScheduler().run(plugin, (ignored) -> task.run(), null);
            } else {
                Bukkit.getScheduler().runTask(plugin, task);
            }
        }

        public static void runTaskLater(Plugin plugin, Entity entity, Runnable task, long delay) {
            if (isFolia) {
                entity.getScheduler().runDelayed(plugin, (ignored) -> task.run(), null, delay);
            } else {
                Bukkit.getScheduler().runTaskLater(plugin, task, delay);
            }
        }

        public static void runTaskTimer(Plugin plugin, Entity entity, Runnable task, long delay, long fixedRate) {
            if (isFolia) {
                entity.getScheduler().runAtFixedRate(plugin, (ignored) -> task.run(), null, delay, fixedRate);
            } else {
                Bukkit.getScheduler().runTaskTimer(plugin, task, delay, fixedRate);
            }
        }
    }
}