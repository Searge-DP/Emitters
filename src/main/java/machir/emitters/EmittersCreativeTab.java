package machir.emitters;

import machir.emitters.block.ModBlocks;
import machir.emitters.util.ModConstants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EmittersCreativeTab extends CreativeTabs {
	public static EmittersCreativeTab INSTANCE = new EmittersCreativeTab();
    
	public EmittersCreativeTab() {
		super(ModConstants.MODID);
	}
	
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return new ItemStack(ModBlocks.emitter).getItem();
    }
}
