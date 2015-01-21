package machir.emitters.network.message;

import io.netty.buffer.ByteBuf;
import machir.emitters.network.PacketHandler;
import machir.emitters.tileentity.TileEmitter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileButton implements IMessage,
		IMessageHandler<MessageTileButton, IMessage> {
	private String buttonName;
	private int x;
	private int y;
	private int z;
	private int dim;
	
	public MessageTileButton() {
	}
	
	/**
	 * @param buttonName the button name which was pressed
	 * @param x the tile x coordinate
	 * @param y the tile y coordinate
	 * @param z the tile z coordinate
	 * @param dim the tile dimension
	 */
	public MessageTileButton(String buttonName, int x, int y, int z, int dim) {
		this.buttonName = buttonName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.dim = dim;
	}

	@Override
	public IMessage onMessage(MessageTileButton message, MessageContext ctx) {
		World world = MinecraftServer.getServer().worldServerForDimension(
				message.dim);
		if (world == null || message.buttonName == null) {
			return null;
		}
		
		// Grab the tile entity and handle the button click
		TileEntity tileEntity = world.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEmitter) {
			((TileEmitter)tileEntity).handleButtonClick(message.buttonName);
			world.markBlockForUpdate(message.x, message.y, message.z);
		}
		
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.buttonName = ByteBufUtils.readUTF8String(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.dim = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.buttonName);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeInt(this.dim);
	}

}
