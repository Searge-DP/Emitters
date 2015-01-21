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
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = ModConstants.MODID)
public class Emitters {
	@Instance(ModConstants.MODID)
	public static Emitters instance;

	@SidedProxy(clientSide = "machir.emitters.client.ClientProxy", serverSide = "machir.emitters.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		logger = evt.getModLog();
		logger.info("Starting Emitters");
	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
		logger.info("Registering blocks");
		ModBlocks.init();
		
		logger.info("Registering tile entities");
		ModBlocks.initTileEntities();
	}
}
