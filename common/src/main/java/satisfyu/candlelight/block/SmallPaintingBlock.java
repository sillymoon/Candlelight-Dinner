package satisfyu.candlelight.block;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class SmallPaintingBlock extends DecorationBlock {
    public SmallPaintingBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(PAINTING, 0));
    }


    public static final IntegerProperty PAINTING = IntegerProperty.create("painting", 0, 6);


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isDiscrete()) return InteractionResult.PASS;
        ItemStack itemStack = player.getItemInHand(hand);
        if (world.isClientSide) {
            if (switchPaintings(world, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }
        }
        return switchPaintings(world, pos, state, player);
    }


    private InteractionResult switchPaintings(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
        world.playSound(null, pos, SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 0.5f, world.getRandom().nextFloat() * 0.1f + 0.9f);
        player.getFoodData().eat(1, 0.4f);
        int i = state.getValue(PAINTING);
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        int nextStage = i + 1;
        if (nextStage <= 6) {
            world.setBlock(pos, state.setValue(PAINTING, nextStage), Block.UPDATE_ALL);
        } else {
            world.setBlock(pos, state.setValue(PAINTING, 0), Block.UPDATE_ALL);
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PAINTING);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.candlelight.decoration.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));

        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("item.candlelight.thankyou1.press_shift").withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("item.candlelight.thankyou2.press_shift").withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD));
            tooltip.add(Component.translatable("item.candlelight.lilitu.press_shift").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.candlelight.baumeisterjo.press_shift").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.candlelight.cristelknight.press_shift").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.candlelight.satisfyu.press_shift").withStyle(ChatFormatting.GOLD));
        } else {
            tooltip.add(Component.translatable("item.candlelight.press_shift.tooltip").withStyle(ChatFormatting.WHITE));
        }
    }
}