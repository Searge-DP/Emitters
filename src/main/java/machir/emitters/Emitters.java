package machir.emitters;

import machir.emitters.block.ModBlocks;
import machir.emitters.util.ModConstants;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.MODID)
public class Emitters {
	@Instance(ModConstants.MODID)
	public static Emitters instance;

	@SidedProxy(clientSide = "machir.emitters.client.ClientProxy", serverSide = "machir.emitters.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent evt) {
		logger = evt.getModLog();
		logger.info("Starting Emitters");
	}

	@EventHandler
	public static void init(FMLInitializationEvent evt) {
		ModBlocks.init();
		ModBlocks.initTileEntities();
	}
}
