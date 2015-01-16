package machir.emitters.block;

import machir.emitters.EmittersCreativeTab;
import machir.emitters.item.block.ItemBlockMod;
import machir.emitters.util.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMod extends Block {
	public BlockMod(Material material) {
		super(material);
		this.setCreativeTab(EmittersCreativeTab.INSTANCE);
	}
	
	@Override
	public Block setBlockName(String name) {
		GameRegistry.registerBlock(this, ItemBlockMod.class, name);
		return super.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(ModConstants.PREFIX
				+ this.getUnlocalizedName().replaceAll("tile\\.", ""));
	}
	
	public void registerBlockIcons_(IIconRegister iconRegister) {
		super.registerBlockIcons(iconRegister);
	}
}
