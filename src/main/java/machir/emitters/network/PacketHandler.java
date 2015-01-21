package machir.emitters.network;

import machir.emitters.Emitters;
import machir.emitters.network.message.MessageTileButton;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public abstract class PacketHandler {
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE
            .newSimpleChannel("Emitters");

    public static void init() {
    	Emitters.logger.info("Registering network messages");
        instance.registerMessage(MessageTileButton.class, MessageTileButton.class,
                0, Side.SERVER);
    }
}
