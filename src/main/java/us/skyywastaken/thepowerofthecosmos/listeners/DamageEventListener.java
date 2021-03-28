package us.skyywastaken.thepowerofthecosmos.listeners;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.thepowerofthecosmos.bypass.MultiArmorBypass;

public class DamageEventListener {
    Logger logger;
    MultiArmorBypass multiArmorBypass;

    public DamageEventListener(org.apache.logging.log4j.Logger passedLogger) {
        logger = passedLogger;
        multiArmorBypass = new MultiArmorBypass();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamageMultiArmor(LivingAttackEvent attackEvent) {
        multiArmorBypass.onLivingAttacked(attackEvent);
    }
}
