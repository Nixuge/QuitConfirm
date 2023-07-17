package me.nixuge.quitconfirm.mixins.gui;

import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.GuiIngameMenu;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameMenu.class)
public class GuiInGameMenuMixin {
    @Unique
    private boolean quitConfirm$hasClicked;
    @Unique
    private final static String quitConfirm$confirmationText = "§cAre you sure?";

    @Inject(method = "initGui", at = @At("RETURN"))
    public void initGui(CallbackInfo ci) {
        quitConfirm$hasClicked = false;
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
    public void actionPerformed(GuiButton button, CallbackInfo ci) {
        if (button.id != 1)
            return;

        if (quitConfirm$hasClicked)
            return;

        quitConfirm$hasClicked = true;
        button.displayString = quitConfirm$confirmationText;
        ci.cancel();
    }
}
