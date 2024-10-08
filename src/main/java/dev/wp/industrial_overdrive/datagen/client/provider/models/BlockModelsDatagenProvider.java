package dev.wp.industrial_overdrive.datagen.client.provider.models;

import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.IOBlocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.registry.holder.BlockHolder;

public final class BlockModelsDatagenProvider extends BlockStateProvider {
    public BlockModelsDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), IO.ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        for (BlockHolder<?> block : IOBlocks.values()) {
            block.modelBuilder().accept(this);
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
