package satisfy.candlelight.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import satisfy.candlelight.registry.BlockEntityRegistry;

public class DinnerBellBlockEntity extends BlockEntity {
    private int ticks = 0;
    private boolean ringed = false;
    private float yOffset = 0.0f;

    public DinnerBellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.DINNER_BELL_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public void onHit() {
        ringed = true;
        ticks = 0;
    }

    @SuppressWarnings("unused")
    public static void clientTick(Level level, BlockPos pos, BlockState state, DinnerBellBlockEntity blockEntity) {
        if (blockEntity.ringed) {
            if (blockEntity.ticks < 20) {
                blockEntity.yOffset = -0.02f * (1.0f - (float)Math.cos(Math.PI * blockEntity.ticks / 20.0f));
            } else {
                blockEntity.yOffset = 0.0f;
                blockEntity.ringed = false;
            }
            blockEntity.ticks++;
        }
    }

    public float getYOffset() {
        return yOffset;
    }
}

