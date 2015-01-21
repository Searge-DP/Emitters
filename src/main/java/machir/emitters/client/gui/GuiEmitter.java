package machir.emitters.client.gui;

import scala.reflect.internal.Trees.This;
import machir.emitters.container.ContainerEmitter;
import machir.emitters.network.PacketHandler;
import machir.emitters.network.message.MessageTileButton;
import machir.emitters.tileentity.TileEmitter;
import machir.emitters.tileentity.TileEmitter.Button;
import machir.emitters.util.GuiConstants;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementButton;

public class GuiEmitter extends GuiBase {
	public static final String TEXTURE = GuiConstants.GUI_PATH + "emitter.png";
	public TileEmitter emitter;
	
	public GuiEmitter(ContainerEmitter container) {
		super(container, new ResourceLocation(TEXTURE));
		this.name = "Particle Emitter";
		this.ySize = 237;
		
		this.emitter = container.emitter;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.addElement(new ElementButton(this, 151, 21, Button.INC_VEL.toString(), 220, 0, 238, 0, 202, 0, 18, 9, TEXTURE));
		this.addElement(new ElementButton(this, 151, 30, Button.DEC_VEL.toString(), 220, 9, 238, 9, 202, 9, 18, 8, TEXTURE));
		this.addElement(new ElementButton(this, 151, 40, Button.INC_SIZE.toString(), 220, 0, 238, 0, 202, 0, 18, 9, TEXTURE));
		this.addElement(new ElementButton(this, 151, 49, Button.DEC_SIZE.toString(), 220, 9, 238, 9, 202, 9, 18, 8, TEXTURE));
		this.addElement(new ElementButton(this, 151, 59, Button.INC_SPREAD.toString(), 220, 0, 238, 0, 202, 0, 18, 9, TEXTURE));
		this.addElement(new ElementButton(this, 151, 68, Button.DEC_SPREAD.toString(), 220, 9, 238, 9, 202, 9, 18, 8, TEXTURE));
		this.addElement(new ElementButton(this, 151, 78, Button.INC_FREQ.toString(), 220, 0, 238, 0, 202, 0, 18, 9, TEXTURE));
		this.addElement(new ElementButton(this, 151, 87, Button.DEC_FREQ.toString(), 220, 9, 238, 9, 202, 9, 18, 8, TEXTURE));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		super.drawGuiContainerForegroundLayer(x, y);
		this.drawPropertyString(85, 26, "Vel.", String.valueOf(this.emitter.velocity));
		this.drawPropertyString(85, 45, "Size.", String.valueOf(this.emitter.size));
		this.drawPropertyString(85, 64, "Spread.", String.valueOf(this.emitter.spread));
		this.drawPropertyString(85, 83, "Freq.", String.valueOf(this.emitter.frequency));
	}
	
	private void drawPropertyString(int x, int y, String text, String value) {
		this.fontRendererObj.drawString(text, x, y, 0x404040);
		this.fontRendererObj.drawString(value, x + 60 - this.fontRendererObj.getStringWidth(value), y, 0x404040);
	}
	
	@Override
	public void handleElementButtonClick(String buttonName, int mouseButton) {
		if (mouseButton == 0) {
			TileEmitter emitter = ((ContainerEmitter)this.inventorySlots).emitter;
			PacketHandler.instance.sendToServer(new MessageTileButton(buttonName, emitter.xCoord, emitter.yCoord, emitter.zCoord, emitter.getWorldObj().provider.dimensionId));
		}
	}
}
