package com.shrub.render.block;

import org.lwjgl.opengl.GL11;

import com.shrub.main.Main;
import com.shrub.model.ModelComputerModern;
import com.shrub.tileentity.TileEntityComputer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderComputerModern extends TileEntitySpecialRenderer {
	
	public static final ResourceLocation texture = new ResourceLocation(Main.modID + ":textures/model/ComputerModern.png");
	
	private ModelComputerModern model;
	
	public RenderComputerModern() {
		this.model = new ModelComputerModern();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		
		boolean computerIsReal = false;
		boolean circuitLoaded = false;
		
		if (tileEntity instanceof TileEntityComputer) {
			TileEntityComputer computer = (TileEntityComputer) tileEntity;
			circuitLoaded = computer.circuitLoaded;
			if (computer.errorCode != 2 && computer.errorCode != 3) {
				computerIsReal = true;
			}
		}
		
		float rotation = 0;
		float rotation2 = 1;
		
		try {
			switch (tileEntity.getBlockMetadata()) {
			case 2:
				rotation = 0F;
				break;
			case 3:
				rotation = 1F;
				rotation2 = 0F;
				break;
			case 4:
				rotation = 1F;
				break;
			case 5:
				rotation = -1F;
				break;
		
			}
		
		} catch (Exception e) {
			//Because the metadata will be null if rendering from inventory
			rotation = 1;
			rotation2 = 1;
			
			if (((TileEntityComputer) tileEntity).errorCode == 3) {
				rotation = 1;
				rotation2 = 0;
			}
			
		}
		
		GL11.glPushMatrix();
			
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180F, rotation, 0F, rotation2);
			this.bindTexture(texture);
		
			GL11.glPushMatrix();
				this.model.renderModel(0.0625F, false, computerIsReal, circuitLoaded);
			GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}

}
