package machir.emitters.block;

import machir.emitters.Emitters;
import machir.emitters.tileentity.TileEmitter;
import machir.emitters.util.BlockConstants;
import machir.emitters.util.GuiConstants;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmitter extends BlockMod implements ITileEntityProvider {
	public BlockEmitter() {
		super(Material.rock);
		this.setStepSound(this.soundTypeStone);
		this.setBlockName(BlockConstants.EMITTER);
		this.setBlockTextureName("stone");
		this.setBlockBounds(0.3f, 0.0f, 0.3f, 0.7f, 0.2f, 0.7f);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        return world.isSideSolid(x - dir.offsetX, y - dir.offsetY, z - dir.offsetZ, dir);
    }
	
	@Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z) {
		switch (ForgeDirection.getOrientation(iBlockAccess.getBlockMetadata(x, y, z))) {
		case UP:
		default:
			this.setBlockBounds(0.3f, 0.0f, 0.3f, 1 - 0.3f, 1 - 0.8f, 1 - 0.3f);
			break;
		case DOWN:
			this.setBlockBounds(0.3f, 0.8f, 0.3f, 1 - 0.3f, 1 - 0.0f, 1 - 0.3f);
			break;
		case NORTH:
			this.setBlockBounds(0.3f, 0.3f, 0.8f, 1 - 0.3f, 1 - 0.3f, 1 - 0.0f);
			break;
		case SOUTH:
			this.setBlockBounds(0.3f, 0.3f, 0.0f, 1 - 0.3f, 1 - 0.3f, 1 - 0.8f);
			break;
		case WEST:
			this.setBlockBounds(0.8f, 0.3f, 0.3f, 1 - 0.0f, 1 - 0.3f, 1 - 0.3f);
			break;
		case EAST:
			this.setBlockBounds(0.0f, 0.3f, 0.3f, 1 - 0.8f, 1 - 0.3f, 1 - 0.3f);
			break;
		}
	}
	
	@Override
    public int onBlockPlaced(World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ, int meta) {
        return ForgeDirection.getOrientation(side).ordinal();
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		entityPlayer.openGui(Emitters.instance, GuiConstants.GUI_EMITTER, world, x, y, z);
		return false;
	}
	
	/**
	 * Override the registerBlockIcons to call the _ method which passes through to the Block class
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.registerBlockIcons_(iconRegister);
	}
	
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEmitter();
    }
}
