/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.module.misc;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_33;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_0337;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_1222;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1328;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.IRCModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.ThreadSafeRandom;
import net.minecraft.world.World;
import nick.Settings;
import org.apache.commons.io.FileUtils;

public class KillEffectsModule
extends Module {
    public Setting<Boolean> field_886;
    public Setting<Boolean> field_887;
    public Setting<Class_0211> field_888;
    public Setting<Float> field_889;
    public Setting<Boolean> field_890;
    public Setting<Class_0211> field_891;
    public Setting<Float> field_892;
    public Setting<Boolean> field_893;
    public Setting<Float> field_894;
    public Setting<Boolean> field_895;
    public Setting<Integer> field_896;
    public Setting<Boolean> field_897;
    public Setting<Boolean> field_898;
    public Setting<Boolean> field_899;
    public Setting<Boolean> field_900;
    public Setting<Boolean> field_901;
    public Setting<Float> field_902;
    public Setting<String> field_903;
    public Setting<Boolean> field_904;
    public Setting<Boolean> field_905;
    public static IRCModule field_906 = Hub.field_2595.method_78(IRCModule.class);
    public static final Random field_907 = new ThreadSafeRandom(System.currentTimeMillis());
    public final ConcurrentHashMap<UUID, Integer> field_908;
    public final ObjectList<UUID> field_909;
    public int field_910;
    public int field_911;
    public List<String> field_912;

    public KillEffectsModule() {
        super("KillEffects", "Does various things when someone dies.", Category.MISC, new String[0]);
        Settings.initialize((Object)this);
        this.field_908 = new ConcurrentHashMap();
        this.field_909 = ObjectLists.synchronize((ObjectList)new ObjectArrayList());
        this.field_910 = 0;
        this.field_911 = 0;
        this.field_912 = Collections.synchronizedList(new ArrayList());
        this.field_887.method_31("KillSoundPlayers");
        this.field_888.method_31("KillSoundPlayersSound");
        this.field_889.method_31("KillSoundPlayersVolume");
        this.field_890.method_31("KillSoundSelf");
        this.field_891.method_31("KillSoundSelfSound");
        this.field_892.method_31("KillSoundSelfVolume");
        this.field_897.method_31("ThunderSelf");
        this.field_894.method_31("KillStreakSoundVolume");
        this.field_900.method_31("AshesSelf");
        this.field_901.method_9(() -> {
            if ((this.field_901.getValue() != null ? this.field_901.getValue().booleanValue() : false)) {
                this.method_99(false);
            }
        });
        this.field_905.method_9(() -> {
            if ((this.field_905.getValue() != null ? this.field_905.getValue().booleanValue() : false)) {
                this.method_99(true);
                this.field_905.method_78(false);
            }
        });
        this.field_904.method_9(() -> {
            this.field_911 = 0;
        });
        this.field_903.method_9(() -> this.method_99(true));
        this.field_903.method_31("AutoEZPath");
        this.field_904.method_31("AutoEZRandom");
        this.field_905.method_31("AutoEZRefresh");
        this.setDrawn(false);
    }

    @Override
    public void onEnable() {
        this.field_910 = 0;
        this.method_99(true);
    }

    @Subscribe
    public void method_2(Event_20 event_20) {
        this.field_910 = 0;
    }

    @Subscribe
    public void method_2(Event_33 event_33) {
        PlayerEntity playerEntity;
        if (event_33.method_213() == PreType.POST) {
            return;
        }
        if (this.method_535()) {
            return;
        }
        Entity entity = event_33.method_11();
        if (entity instanceof PlayerEntity && (playerEntity = (PlayerEntity)entity) != KillEffectsModule.field_4219.player && !Hub.field_2603.method_1009(playerEntity.getName().getString()) && Class_0396.method_2((Entity)playerEntity) > 0.0f) {
            try {
                this.field_908.put(playerEntity.getUuid(), KillEffectsModule.field_4219.player.age);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Subscribe
    public void method_5(Event_4 event_4) {
        if (event_4.method_127() instanceof DeathMessageS2CPacket) {
            this.field_910 = -1;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Subscribe
    public void method_2(Event_1 event_1) {
        Object object;
        if (this.method_535()) {
            return;
        }
        if (this.field_910 == -1) {
            this.field_910 = 0;
            if ((this.field_886.getValue() != null ? this.field_886.getValue().booleanValue() : false) && (this.field_890.getValue() != null ? this.field_890.getValue().booleanValue() : false) && (this.field_892.getValue() != null ? this.field_892.getValue().floatValue() : 0.0f) > 0.0f && (object = Hub.field_2606.method_2(this.field_891.getValue())) != null) {
                ((Class_0337)object).method_230((this.field_892.getValue() != null ? this.field_892.getValue().floatValue() : 0.0f));
            }
            if ((this.field_895.getValue() != null ? this.field_895.getValue().booleanValue() : false) && (this.field_897.getValue() != null ? this.field_897.getValue().booleanValue() : false)) {
                this.method_36(KillEffectsModule.field_4219.player.getPos());
            }
            if ((this.field_899.getValue() != null ? this.field_899.getValue().booleanValue() : false) && (this.field_900.getValue() != null ? this.field_900.getValue().booleanValue() : false)) {
                this.method_14(KillEffectsModule.field_4219.player.getPos());
            }
        }
        this.field_909.clear();
        object = this.field_908;
        synchronized (object) {
            for (UUID uUID : this.field_908.keySet()) {
                try {
                    Object object2;
                    PlayerEntity playerEntity = KillEffectsModule.field_4219.world.getPlayerByUuid(uUID);
                    if (playerEntity == null) {
                        this.field_909.add(uUID);
                        continue;
                    }
                    int n = KillEffectsModule.field_4219.player.age - this.field_908.get(uUID);
                    if (n > 20) {
                        this.field_909.add(uUID);
                        continue;
                    }
                    PlayerEntity playerEntity2 = playerEntity;
                    if (!(playerEntity2.getHealth() <= 0.0f)) continue;
                    this.field_909.add(uUID);
                    ++this.field_910;
                    if ((this.field_901.getValue() != null ? this.field_901.getValue().booleanValue() : false) && (object2 = this.method_311()) != null) {
                        object2 = ((String)object2).replace("{name}", playerEntity2.getName().getString());
                        if (!field_906.isToggled() || !((String)object2).startsWith(KillEffectsModule.field_906.field_567.getValue())) {
                            String string = (String) (object2);
                            // Original indy site was inlined to a Runnable lambda that opened a
                            // chat message; ForwarderInliner removed the lambda target so we
                            // approximate with a no-op Runnable.
                            Runnable runnable = () -> {};
                            if (this.field_902.method_105()) {
                                runnable.run();
                            } else {
                                Hub.field_2619.method_2(runnable, (int)((this.field_902.getValue() != null ? this.field_902.getValue().floatValue() : 0.0f) / Float.intBitsToFloat(1028443341)));
                            }
                        }
                    }
                    if ((this.field_895.getValue() != null ? this.field_895.getValue().booleanValue() : false) && (Class_0382.method_29((LivingEntity)playerEntity) || !(this.field_898.getValue() != null ? this.field_898.getValue().booleanValue() : false))) {
                        this.method_36(playerEntity.getPos());
                    }
                    if ((this.field_899.getValue() != null ? this.field_899.getValue().booleanValue() : false)) {
                        this.method_14(playerEntity.getPos());
                    }
                    if ((this.field_886.getValue() != null ? this.field_886.getValue().booleanValue() : false) && (this.field_887.getValue() != null ? this.field_887.getValue().booleanValue() : false) && (this.field_889.getValue() != null ? this.field_889.getValue().floatValue() : 0.0f) > 0.0f && Hub.field_2606 != null && (object2 = Hub.field_2606.method_2(this.field_888.getValue())) != null) {
                        ((Class_0337)object2).method_230((this.field_889.getValue() != null ? this.field_889.getValue().floatValue() : 0.0f));
                    }
                    if (!(this.field_893.getValue() != null ? this.field_893.getValue().booleanValue() : false) || this.field_910 < 2 || !((this.field_894.getValue() != null ? this.field_894.getValue().floatValue() : 0.0f) > 0.0f) || Hub.field_2606 == null || (object2 = Hub.field_2606.method_6(this.field_910)) == null) continue;
                    ((Class_0337)object2).method_230((this.field_894.getValue() != null ? this.field_894.getValue().floatValue() : 0.0f));
                }
                catch (Exception exception) {}
            }
        }
        object = this.field_909;
        synchronized (object) {
            try {
                for (UUID uUID : this.field_909) {
                    this.field_908.remove(uUID);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void method_185(PlayerEntity playerEntity) {
        if (!this.method_535() && playerEntity != KillEffectsModule.field_4219.player && !Hub.field_2603.method_1009(playerEntity.getName().getString())) {
            try {
                this.field_908.put(playerEntity.getUuid(), KillEffectsModule.field_4219.player.age);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public void method_99(boolean bl) {
        block6: {
            if (!(this.field_901.getValue() != null ? this.field_901.getValue().booleanValue() : false) && !bl) {
                return;
            }
            Path path = Class_1222.method_2(Class_1328.field_4284.resolve(this.field_903.getValue()), new String[]{".txt"});
            try {
                if (!path.toFile().exists()) {
                    throw new IOException("AutoEZ file %s not found".formatted(this.field_903.getValue()));
                }
                this.field_912.clear();
                this.field_912.addAll(FileUtils.readLines((File)path.toFile(), (Charset)StandardCharsets.UTF_8));
                this.field_911 = 0;
            }
            catch (Exception exception) {
                if (!(this.field_901.getValue() != null ? this.field_901.getValue().booleanValue() : false)) break block6;
                ChatUtil.method_2((Text)Text.literal((String)new TextBuilder().method_2((Object)String.valueOf(exception)).method_9("Failed to update AutoEZ lines: \u0001")).styled(style -> style.withColor(Formatting.RED)), ChatUtil.method_38(-9634482), Priority.LOW);
                try {
                    Files.createFile(path, new FileAttribute[0]);
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
    }

    public String method_311() {
        try {
            if (this.field_912.isEmpty()) {
                return null;
            }
            if ((this.field_904.getValue() != null ? this.field_904.getValue().booleanValue() : false)) {
                return this.field_912.get(ThreadLocalRandom.current().nextInt(this.field_912.size()));
            }
            return this.field_912.get(this.field_911++ % this.field_912.size());
        }
        catch (Exception exception) {
            return null;
        }
    }

    public void method_36(Vec3d vec3d) {
        for (int i = 0; i < this.field_896.getValue(); ++i) {
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, (World)KillEffectsModule.field_4219.world);
            lightningEntity.setPosition(vec3d);
            lightningEntity.refreshPositionAfterTeleport(vec3d);
            KillEffectsModule.field_4219.world.addEntity((Entity)lightningEntity);
        }
    }

    public void method_14(Vec3d vec3d) {
        for (int i = 0; i < field_907.nextInt(35) + 25; ++i) {
            Particle particle = KillEffectsModule.field_4219.particleManager.addParticle((ParticleEffect)(field_907.nextBoolean() ? ParticleTypes.ASH : ParticleTypes.WHITE_ASH), vec3d.getX() + field_907.nextGaussian() * Double.longBitsToDouble(4597454643433098445L), vec3d.getY() + Double.longBitsToDouble(0x4000000000000000L) - field_907.nextGaussian() * Double.longBitsToDouble(4602949035107339469L), vec3d.getZ() + field_907.nextGaussian() * Double.longBitsToDouble(4597454643433098445L), 0.0, Double.longBitsToDouble(-4631501856787818086L) - field_907.nextGaussian() * Double.longBitsToDouble(4602678819172646912L), 0.0);
            if (particle == null) continue;
            particle.maxAge += 15;
        }
    }
}

