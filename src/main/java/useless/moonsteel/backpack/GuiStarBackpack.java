package useless.moonsteel.backpack;

import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.lang.I18n;
import org.lwjgl.opengl.GL11;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import useless.moonsteel.ContainerStarBackpack;

public class GuiStarBackpack extends ScreenContainerAbstract {
	private int guiX;
	private int guiY;
	private int rows;
	private int slotsNum;
	private final ContainerStarBackpack backpack;
	public GuiStarBackpack(Player player) {
		super(new ContainerStarBackpack(player));
		backpack = (ContainerStarBackpack) inventorySlots;
	}

	@Override
	public void init() {
		this.guiX = (this.width - this.xSize) / 2;
		this.guiY = (this.height - this.ySize) / 2;
		this.slotsNum = this.backpack.backpackInventory.getContainerSize();
		this.rows = (int)Math.ceil(this.slotsNum / 9.0);
		super.init();
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		String name = I18n.getInstance().translateKey(this.backpack.backpackInventory.getNameTranslationKey());
		this.drawStringNoShadow(this.fontRenderer, name, 8, 6, BetterWithBackpacks.GUI_LABEL_COLOR);
		this.drawStringNoShadow(this.fontRenderer, "Inventory", 8, this.ySize - 96 + 2, BetterWithBackpacks.GUI_LABEL_COLOR);
	}

	protected void drawGuiContainerBackgroundLayer(float f) {
		GL11.glColor3d(1.0, 1.0, 1.0);
		this.mc.textureManager.loadTexture("/assets/betterwithbackpacks/gui/backpack.png").bind();
		this.drawTexturedModalRect(this.guiX, this.guiY, 0, 0, this.xSize, this.ySize);

		for(int i = 0; i < this.rows; ++i) {
			if (i == this.rows - 1) {
				this.drawTexturedModalRect(this.guiX + 7, this.guiY + 17 + 18 * i, 0, 166, 18 * (this.slotsNum - 9 * i), 18);
			} else {
				this.drawTexturedModalRect(this.guiX + 7, this.guiY + 17 + 18 * i, 0, 166, 162, 18);
			}
		}
	}
}
