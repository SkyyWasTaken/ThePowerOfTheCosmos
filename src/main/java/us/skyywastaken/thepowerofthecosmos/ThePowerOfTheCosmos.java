package us.skyywastaken.thepowerofthecosmos;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.thepowerofthecosmos.listeners.DamageEventListener;

@Mod(modid = ThePowerOfTheCosmos.MODID, name = ThePowerOfTheCosmos.NAME, version = ThePowerOfTheCosmos.VERSION, acceptableRemoteVersions = "*")
public class ThePowerOfTheCosmos
{
    public static final String MODID = "thepowerofthecosmos";
    public static final String NAME = "The Power Of The Cosmos";
    public static final String VERSION = "1.0";
    private DamageEventListener damageEventListener;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        damageEventListener = new DamageEventListener(logger);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("preparing to destr0y multi armor");
        MinecraftForge.EVENT_BUS.register(damageEventListener);
    }
}
