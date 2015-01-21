package machir.emitters.client.gui.element;

import machir.emitters.util.GuiConstants;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementButtonManaged;

public abstract class ElementButtonManagedAdv extends ElementButtonManaged {
	public static final ResourceLocation HOVER = new ResourceLocation(GuiConstants.ELEMENTS_PATH + "button_hover.png");
	public static final ResourceLocation ENABLED = new ResourceLocation(GuiConstants.ELEMENTS_PATH + "button_enabled.png");
	public static final ResourceLocation DISABLED = new ResourceLocation(GuiConstants.ELEMENTS_PATH + "button_disabled.png");
	
	public ElementButtonManagedAdv(GuiBase containerScreen, int posX, int posY,
			int sizeX, int sizeY, String text) {
		super(containerScreen, posX, posY, sizeX, sizeY, text);
	}
	
	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks) {

		if (!isEnabled()) {
			gui.bindTexture(DISABLED);
		} else if (intersectsWith(mouseX, mouseY)) {
			gui.bindTexture(HOVER);
		} else {
			gui.bindTexture(ENABLED);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(posX, posY, 0, 0, sizeX / 2, sizeY / 2);
		drawTexturedModalRect(posX, posY + sizeY / 2, 0, 256 - sizeY / 2, sizeX / 2, sizeY / 2);
		drawTexturedModalRect(posX + sizeX / 2, posY, 256 - sizeX / 2, 0, sizeX / 2, sizeY / 2);
		drawTexturedModalRect(posX + sizeX / 2, posY + sizeY / 2, 256 - sizeX / 2, 256 - sizeY / 2, sizeX / 2, sizeY / 2);
	}
}
