package useless.moonsteel;

import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.lang.I18n;
import org.lwjgl.opengl.GL11;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;

public class GuiStarBackpack extends ScreenContainerAbstract {
    private int GUIx;
    private int GUIy;
    private int rows;
    private int slotsNum;
    private final ContainerStarBackpack backpack;

    public GuiStarBackpack(Player player) {
        super(new ContainerStarBackpack(player));
        backpack = (ContainerStarBackpack) inventorySlots;
    }

    @Override
    public void init() {
        this.GUIx = (this.width - this.xSize) / 2;
        this.GUIy = (this.height - this.ySize) / 2;
        this.slotsNum = this.backpack.backpackInventory.getContainerSize();
        this.rows = (int) Math.ceil( this.slotsNum / 9.0);
        super.init();
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        this.font.drawString(I18n.getInstance().translateKey(this.backpack.backpackInventory.getNameTranslationKey()), 8, 6, BetterWithBackpacks.GUI_LABEL_COLOR);
        this.font.drawString("Inventory", 8, this.ySize - 96 + 2, BetterWithBackpacks.GUI_LABEL_COLOR);
    }

    protected void drawGuiContainerBackgroundLayer(float f) {
        GL11.glColor3d(1.0, 1.0, 1.0);
        this.mc.textureManager.loadTexture("/assets/betterwithbackpacks/gui/backpack.png").bind();
        this.drawTexturedModalRect(this.GUIx, this.GUIy, 0, 0, this.xSize, this.ySize);

        for (int i = 0; i < this.rows; ++i) {
            if (i == this.rows - 1) {
                this.drawTexturedModalRect(this.GUIx + 7, this.GUIy + 17 + 18 * i, 0, 166, 18 * (this.slotsNum - 9 * i), 18);
            } else {
                this.drawTexturedModalRect(this.GUIx + 7, this.GUIy + 17 + 18 * i, 0, 166, 162, 18);
            }
        }
    }
}
