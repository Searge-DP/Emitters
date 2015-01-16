package machir.emitters.item.block;

import machir.emitters.util.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMod extends ItemBlock {
	public ItemBlockMod(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedNameInefficiently(ItemStack itemStack) {
		return super.getUnlocalizedName(itemStack).replaceAll("tile.", "tile." + ModConstants.PREFIX);
	}
}
