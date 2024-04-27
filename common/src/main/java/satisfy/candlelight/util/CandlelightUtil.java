package satisfy.candlelight.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import satisfy.candlelight.client.gui.NotePaperGui;
import satisfy.candlelight.client.gui.SignedPaperGui;
import satisfy.candlelight.client.gui.TypeWriterGui;
import satisfy.candlelight.entity.TypeWriterEntity;

public class CandlelightUtil {
    public static void setNotePaperScreen(Player user, ItemStack stack, InteractionHand hand){
        Minecraft.getInstance().setScreen(new NotePaperGui(user, stack, hand));
    }


    public static void setTypeWriterScreen(Player user, TypeWriterEntity typeWriterEntity){
        if(user instanceof LocalPlayer)
            Minecraft.getInstance().setScreen(new TypeWriterGui(user, typeWriterEntity));
    }

    public static void setSignedPaperScreen(ItemStack stack){
        Minecraft.getInstance().setScreen(new SignedPaperGui(new SignedPaperGui.WrittenPaperContents(stack)));
    }
}