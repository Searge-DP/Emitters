package machir.emitters.container;

import machir.emitters.tileentity.TileEmitter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEmitter extends Container {
	public final TileEmitter emitter;

	public ContainerEmitter(InventoryPlayer inventory, TileEmitter emitter) {
		this.emitter = emitter;
		this.bindPlayerInventory(inventory, 8, 155);
	}
	
	protected void bindPlayerInventory(InventoryPlayer invPlayer, int xOffset, int yOffset) {
        // Main inventory rows
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, xOffset + j * 18, yOffset + i * 18));
            }
        }
        
		// Hotbar row
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, xOffset + i * 18, yOffset + 58));
        }
    }
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack itemStack = null;
        Slot slotObject = (Slot) this.inventorySlots.get(slot);
        
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            itemStack = stackInSlot.copy();
            
            if (slot <= 0) {
                if (!this.mergeItemStack(stackInSlot, 0, 37, true)) {
                    return null;
                }
            } /*else {
                if (stackInSlot != null && stackInSlot.getItem() instanceof ItemEnderPearl) {
                    if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
                        return null;
                    }
                }
            }*/
            
            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }
            
            if (stackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return itemStack;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}
}
