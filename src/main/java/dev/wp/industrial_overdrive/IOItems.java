package dev.wp.industrial_overdrive;

import com.google.common.collect.Sets;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.swedz.tesseract.neoforge.registry.SortOrder;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

import java.util.Set;
import java.util.function.Function;

public final class IOItems {
    public static final class Registry {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IO.ID);
        private static final Set<ItemHolder> HOLDERS = Sets.newHashSet();

        private static void init(IEventBus bus) {
            ITEMS.register(bus);
        }

        public static void include(ItemHolder holder) {
            HOLDERS.add(holder);
        }
    }

    public static void init(IEventBus bus) {
        Registry.init(bus);
    }

    public static Set<ItemHolder> values() {
        return Set.copyOf(Registry.HOLDERS);
    }

    public static ItemHolder valueOf(String id) {
        return Registry.HOLDERS.stream()
                .filter((holder) -> holder.identifier().id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public static <Type extends Item> ItemHolder<Type> create(String id, String englishName, Function<Item.Properties, Type> creator, SortOrder sortOrder) {
        ItemHolder<Type> holder = new ItemHolder<>(IO.id(id), englishName, Registry.ITEMS, creator).sorted(sortOrder);
        Registry.include(holder);
        return holder;
    }
}
