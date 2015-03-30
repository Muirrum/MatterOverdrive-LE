package com.MO.MatterOverdrive.blocks;

import cofh.lib.util.helpers.BlockHelper;
import com.MO.MatterOverdrive.MatterOverdrive;
import com.MO.MatterOverdrive.blocks.includes.MOBlockContainer;
import com.MO.MatterOverdrive.init.MatterOverdriveIcons;
import com.MO.MatterOverdrive.tile.TileEntityMachinePatternStorage;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Simeon on 3/27/2015.
 */
public class BlockPatternStorage extends MOBlockContainer
{

    public BlockPatternStorage(Material material, String name)
    {
        super(material, name);

    }

    @Override
    public boolean onBlockActivated(World world,int x,int y,int z,EntityPlayer player,int side,float hitX,float hitY,float hitZ)
    {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
        if(!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, MatterOverdrive.instance, MatterOverdrive.guiPatternStorage, world, x, y, z);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        super.registerBlockIcons(iconRegister);
        this.blockIcon = MatterOverdriveIcons.Base;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if(side == BlockHelper.getOppositeSide(metadata))
        {
            return MatterOverdriveIcons.Vent;
        }

        return this.blockIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityMachinePatternStorage();
    }
}
