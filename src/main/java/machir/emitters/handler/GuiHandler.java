package machir.emitters.handler;

import machir.emitters.client.gui.GuiEmitter;
import machir.emitters.container.ContainerEmitter;
import machir.emitters.tileentity.TileEmitter;
import machir.emitters.util.GuiConstants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
        case GuiConstants.GUI_EMITTER:
        	TileEntity tileEntity = world.getTileEntity(x, y, z);
        	if (tileEntity instanceof TileEmitter) {
        		return new ContainerEmitter(player.inventory, (TileEmitter)tileEntity);
        	}
            break;
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
        case GuiConstants.GUI_EMITTER:
        	TileEntity tileEntity = world.getTileEntity(x, y, z);
        	if (tileEntity instanceof TileEmitter) {
        		return new GuiEmitter(new ContainerEmitter(player.inventory, (TileEmitter)tileEntity));
        	}
            break;
        }
		return null;
	}

}
