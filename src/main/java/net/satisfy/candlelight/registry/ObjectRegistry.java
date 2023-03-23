package net.satisfy.candlelight.registry;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.satisfy.candlelight.Candlelight;
import net.satisfy.candlelight.block.CakeBlock;
import net.satisfy.candlelight.block.*;
import net.satisfy.candlelight.block.LanternBlock;
import net.satisfy.candlelight.block.*;
import net.satisfy.candlelight.item.*;
import net.satisfy.candlelight.util.CandlelightIdentifier;
import net.satisfy.candlelight.util.CropType;
import net.satisfy.candlelight.world.feature.ConfiguredFeatures;
import org.jetbrains.annotations.Nullable;
import satisfyu.vinery.block.*;
import satisfyu.vinery.block.FacingBlock;
import satisfyu.vinery.block.*;
import satisfyu.vinery.item.DrinkBlockBigItem;
import satisfyu.vinery.item.DrinkBlockItem;
import satisfyu.vinery.registry.VineryEffects;
import satisfyu.vinery.registry.VinerySoundEvents;
import satisfyu.vinery.util.VineryFoodComponent;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static satisfyu.vinery.registry.ObjectRegistry.CHERRY_PLANKS;


public class ObjectRegistry {

    Item.Settings settings = new Item.Settings();

    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();
    public static final Block TOMATO_CROP = register("tomato_crop", new PickCropBlock(getBushSettings(), CropType.TOMATO), false);
    public static final Item TOMATO_SEEDS = register("tomato_seeds", new CropSeedItem(TOMATO_CROP, getSettings(), CropType.TOMATO));
    public static final Item TOMATO = register("tomato", new IngredientItem(getSettings().food(FoodComponents.APPLE)));
    public static final Block TOMATO_CRATE = register("tomato_crate", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Item BROCCOLI_SEEDS = register("broccoli_seeds", new AliasedBlockItem(ObjectRegistry.BROCCOLI_CROP, getSettings()));
    public static final Block BROCCOLI_CROP = register("broccoli_crop", new BroccoliCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)), false);
    public static final Item BROCCOLI = register("broccoli", new IngredientItem(getSettings().food(FoodComponents.POTATO)));
    public static final Block BROCCOLI_CRATE = register("broccoli_crate", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRAWBERRY_CROP = register("strawberry_crop", new PickCropBlock(getBushSettings(), CropType.STRAWBERRY), false);
    public static final Item STRAWBERRY_SEEDS = register("strawberry_seeds", new CropSeedItem(STRAWBERRY_CROP, getSettings(), CropType.STRAWBERRY));
    public static final Item STRAWBERRY = register("strawberry", new IngredientItem(getSettings().food(FoodComponents.BEETROOT)));
    public static final Block STRAWBERRY_CRATE = register("strawberry_crate", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRAWBERRY_WILD_TAIGA = register("strawberry_wild_taiga", new PickCropBlock(getBushSettings(), CropType.STRAWBERRY), false);
    public static final Block STRAWBERRY_WILD_JUNGLE = register("strawberry_wild_jungle", new PickCropBlock(getBushSettings(), CropType.STRAWBERRY), false);
    public static final Block TOMATOES_WILD = register("tomatoes_wild", new PickCropBlock(getBushSettings(), CropType.TOMATO), false);
    public static final Block SOFA = register("sofa", new SofaBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CHAIR = register("chair", new ChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block TABLE = register("table", new TableBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));
    public static final Block LAMP = register("lamp", new LanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).luminance(s -> s.get(LanternBlock.LUMINANCE) ? 15 : 0).sounds(BlockSoundGroup.WOOD)));
    public static final Block DRAWER = register("drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block CABINET = register("cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));
    public static final Block SIDEBOARD = register("sideboard", new SideBoardBlock(FabricBlockSettings.copyOf(Blocks.CHEST)));
    public static final Block FLOORBOARD = register("floorboard", new Block(FabricBlockSettings.copy(CHERRY_PLANKS)));
    public static final Block CAKE_STAND = register("cake_stand", new CakeStandBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block TRAY = register("tray", new TrayBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block COOKING_POT = register("cooking_pot", new CookingPotBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()));
    public static final Block COOKING_PAN = register("cooking_pan", new CookingPanBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()));

    public static final Block TABLE_SET = register("table_set", new TableSetBlock(FabricBlockSettings.copyOf(COOKING_POT)));
    public static final Item BUTTER = register("butter", new IngredientItem(getSettings()));
    public static final Item CHOCOLATE = register("chocolate", new IngredientItem(getSettings().food(FoodComponents.COOKIE)));
    public static final Item MOZZARELLA = register("mozzarella", new IngredientItem(getSettings().food(FoodComponents.BREAD)));
    public static final Item TOMATO_SOUP = register("tomato_soup", new IngredientItem(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item BEETROOT_SALAD = register("beetroot_salad", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item COOKED_BEEF = register("cooked_beef", new Item(getSettings().food(FoodComponents.COOKED_BEEF)));
    public static final Item PASTA = register("pasta", new Item(getSettings().food(FoodComponents.COOKED_MUTTON)));
    public static final Item BEEF_TARTARE = register("beef_tartare", new Item(getSettings().food(FoodComponents.COOKED_BEEF)));
    // public static final  Block    TOMATO_MOZZARELLA_SALAT = register("tomato_mozzarella_salat", new MealBlock(getSettings()
    public static final Item BROCCOLI_BEEF = register("broccoli_beef", new Item(getSettings().food(FoodComponents.GOLDEN_CARROT)));
    public static final Item BROCCOLI_TOMATO = register("broccoli_tomato", new Item(getSettings().food(FoodComponents.GOLDEN_CARROT)));
    // public static final  Item     ROASTBEEF_CARROTS = register("roastbeef_carrots", new MealBlock;
    public static final Item SALMON = register("salmon", new Item(getSettings().food(FoodComponents.GOLDEN_CARROT)));
    public static final Item VEGGIE_PLATE = register("veggie_plate", new Item(getSettings().food(FoodComponents.COOKED_BEEF)));
    public static final Item PORK_RIB = register("pork_rib", new Item(getSettings().food(FoodComponents.GOLDEN_CARROT)));
    // public static final  Block    LASAGNA = register("lasagna", new MealBlock
    // public static final  Block    BEEF_WELLINGTON = register("beef_wellington", new MealBlock
    public static final Item MASHED_POTATOES = register("mashed_potatoes", new Item(getSettings().food(FoodComponents.GOLDEN_CARROT)));
    public static final Item FRICASSE = register("fricasse", new Item(getSettings().food(FoodComponents.GOLDEN_APPLE)));
    public static final Item FRIED_EGG = register("fried_egg", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Block STRAWBERRY_JAM = register("strawberry_jam", new StackableBlock(FabricBlockSettings.of(Material.GLASS).breakInstantly().nonOpaque().sounds(BlockSoundGroup.GLASS)));
    public static final Item VINEGAR = register("vinegar", new IngredientItem(getSettings()));
    public static final Block RED_WINE = registerWine("red_wine", new WineBottleBlock(getWineSettings(), 3), VineryEffects.IMPROVED_FIRE_RESISTANCE);
    public static final Block PRAETORIAN_WINE = registerBigWine("praetorian_wine", new WineBottleBlock(getWineSettings(), 3), VineryEffects.IMPROVED_JUMP_BOOST);
    public static final Item PANCAKE = register("pancake", new Item(getSettings().food(FoodComponents.BAKED_POTATO)));
    public static final Item WAFFLE = register("waffle", new Item(getSettings().food(FoodComponents.BAKED_POTATO)));
    public static final Item STRAWBERRY_GLAZED_COOKIE = register("strawberry_glazed_cookie", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Item SWEETBERRY_GLAZED_COOKIE = register("sweetberry_glazed_cookie", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Item CHOCOLATE_GLAZED_COOKIE = register("chocolate_glazed_cookie", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Item CROISSANT = register("croissant", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Item BUNDT_CAKE = register("bundt_cake", new Item(getSettings().food(FoodComponents.COOKED_BEEF)));
    public static final Block SESAM_BREAD = register("sesam_bread", new BreadBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque()));
    public static final Item STRAWBERRY_CHOCOLATE = register("strawberry_chocolate", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Item STRAWBERRY_CAKE_SLICE = register("strawberry_cake_slice", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Block STRAWBERRY_CAKE = register("strawberry_cake", new CakeBlock((FabricBlockSettings.copyOf(Blocks.CAKE)), ObjectRegistry.STRAWBERRY_CAKE_SLICE));
    public static final Item STRAWBERRY_MILKSHAKE = register("strawberry_milkshake", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item STRAWBERRY_ICECREAM = register("strawberry_icecream", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item SWEETBERRY_CAKE_SLICE = register("sweetberry_cake_slice", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Block SWEETBERRY_CAKE = register("sweetberry_cake", new CakeBlock((FabricBlockSettings.copyOf(Blocks.CAKE)), ObjectRegistry.SWEETBERRY_CAKE_SLICE));
    public static final Item SWEETBERRY_MILKSHAKE = register("sweetberry_milkshake", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item SWEETBERRY_ICECREAM = register("sweetberry_icecream", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item CHOCOLATE_CAKE_SLICE = register("chocolate_cake_slice", new Item(getSettings().food(FoodComponents.BREAD)));
    public static final Block CHOCOLATE_CAKE = register("chocolate_cake", new CakeBlock((FabricBlockSettings.copyOf(Blocks.CAKE)), ObjectRegistry.CHOCOLATE_CAKE_SLICE));
    public static final Item CHOCOLATE_MILKSHAKE = register("chocolate_milkshake", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Item CHOCOLATE_ICECREAM = register("chocolate_icecream", new Item(getSettings().food(FoodComponents.BEETROOT_SOUP)));
    public static final Block TABLE_SIGN = register("table_sign", new BoardBlock(FabricBlockSettings.of(Material.DECORATION), false));
    public static final Block STREET_SIGN = register("street_sign", new BoardBlock(FabricBlockSettings.of(Material.DECORATION), true));
    public static final Block PAINTING = register("painting", new DecorationBlock(FabricBlockSettings.of(Material.DECORATION).noCollision()));
    public static final Block HEARTH = register("hearth", new DecorationBlock(FabricBlockSettings.of(Material.DECORATION).noCollision()));
    public static final Block ROSE = register("rose", new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block APPLE_TREE_SAPLING = register("apple_tree_sapling", new SaplingBlock(new SaplingGenerator() {
        @Nullable
        @Override
        protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            if (random.nextBoolean()) {
                if (bees) return ConfiguredFeatures.APPLE_TREE_BEE;
                return ConfiguredFeatures.APPLE_TREE;
            } else {
                if (bees) return ConfiguredFeatures.APPLE_TREE_VARIANT_WITH_BEE;
                return ConfiguredFeatures.APPLE_TREE_VARIANT;
            }
        }
    }, AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)), true);
    public static final Block APPLE_LEAVES = register("apple_leaves", new AppleLeaves(FabricBlockSettings.copy(Blocks.OAK_LEAVES)));
    public static final Block CANDLE = register("candle", new CandleBlock(FabricBlockSettings.of(Material.DECORATION).noCollision()));
    public static final Block JEWELRY_BOX = register("jewelry_box", new JewelryBoxBlock(FabricBlockSettings.of(Material.DECORATION)));
    public static final Block CHOCOLATE_BOX = register("chocolate_box", new ChocolateBoxBlock(FabricBlockSettings.copy(Blocks.CAKE)));
    public static final Item COOKING_HAT = register("cooking_hat", new CookingHatItem(getSettings().rarity(Rarity.COMMON)));
    public static final Item CHEFS_JACKET = register("chefs_jacket", new CookDefaultArmorItem(CandlelightMaterials.COOK_ARMOR, EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON)));
    public static final Item CHEFS_PANTS = register("chefs_pants", new CookDefaultArmorItem(CandlelightMaterials.COOK_ARMOR, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final Item CHEFS_BOOTS = register("chefs_boots", new CookDefaultArmorItem(CandlelightMaterials.COOK_ARMOR, EquipmentSlot.FEET, getSettings().rarity(Rarity.COMMON)));
    public static final Item GLASS = register("glass", new Item(getSettings()));
    public static final Item NAPKIN = register("napkin", new Item(getSettings()));
    public static final Block BOOK = register("book", new FacingBlock(FabricBlockSettings.of(Material.DECORATION)));
    public static final Item NOTE_PAPER = register("note_paper", new Item(getSettings()));
    public static final Item NOTE_PAPER_WRITEABLE = register("note_paper_writeable", new WriteablePaperItem(getSettings().maxCount(1)));
    public static final Item NOTE_PAPER_WRITTEN = register("note_paper_written", new WrittenPaperItem(getSettings()));
    public static final Block TYPEWRITER_IRON = register("typewriter_iron", new TypeWriterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block TYPEWRITER_COPPER = register("typewriter_copper", new TypeWriterBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Item LETTER_OPEN = register("letter_open", new LetterItem(getSettings()));
    public static final Item LETTER_CLOSED = register("letter_closed", new ClosedLetterItem(getSettings().maxCount(1)));
    public static final Item LOVE_LETTER = register("love_letter", new Item(getSettings()));
    public static final Block WINE_STATION = register("wine_station", new WineStationBlock(FabricBlockSettings.of(Material.WOOD).nonOpaque()));

    public static final Item GOLD_RING = register("gold_ring", new Item(getSettings()));
    /**
     * Armor Item: Usable on Chest. Will give Luck +2.
     */

    public static final Block OAK_WINE_RACK_BIG = register("oak_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block OAK_WINE_RACK_SMALL = register("oak_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block OAK_CABINET = register("oak_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block OAK_DRAWER = register("oak_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));
    public static final Block COBBLESTONE_WOOD_FIRED_OVEN = register("cobblestone_wood_fired_oven", new WoodFiredOvenBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(state -> state.get(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final Block COBBLESTONE_STOVE = register("cobblestone_stove", new StoveBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(12)));
    public static final Block COBBLESTONE_KITCHEN_SINK = register("cobblestone_kitchen_sink", new KitchenSinkBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque()));

    public static final Block BIRCH_WINE_RACK_BIG = register("birch_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block BIRCH_WINE_RACK_SMALL = register("birch_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block BIRCH_CABINET = register("birch_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block BIRCH_DRAWER = register("birch_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));
    public static final Block SANDSTONE_WOOD_FIRED_OVEN = register("sandstone_wood_fired_oven", new WoodFiredOvenBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(state -> state.get(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final Block SANDSTONE_STOVE = register("sandstone_stove", new StoveBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(12)));
    public static final Block SANDSTONE_KITCHEN_SINK = register("sandstone_kitchen_sink", new KitchenSinkBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque()));

    public static final Block SPRUCE_WINE_RACK_BIG = register("spruce_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block SPRUCE_WINE_RACK_SMALL = register("spruce_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block SPRUCE_CABINET = register("spruce_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block SPRUCE_DRAWER = register("spruce_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));
    public static final Block BRICKS_WOOD_FIRED_OVEN = register("bricks_wood_fired_oven", new WoodFiredOvenBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(state -> state.get(WoodFiredOvenBlock.LIT) ? 13 : 0)));
    public static final Block BRICKS_STOVE = register("bricks_stove", new StoveBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(12)));
    public static final Block BRICKS_KITCHEN_SINK = register("bricks_kitchen_sink", new KitchenSinkBlock(FabricBlockSettings.copy(Blocks.STONE).nonOpaque()));

    public static final Block DARK_OAK_WINE_RACK_BIG = register("dark_oak_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DARK_OAK_WINE_RACK_SMALL = register("dark_oak_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block DARK_OAK_CABINET = register("dark_oak_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block DARK_OAK_DRAWER = register("dark_oak_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));


    public static final Block ACACIA_WINE_RACK_BIG = register("acacia_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block ACACIA_WINE_RACK_SMALL = register("acacia_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block ACACIA_CABINET = register("acacia_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block ACACIA_DRAWER = register("acacia_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));


    public static final Block JUNGLE_WINE_RACK_BIG = register("jungle_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block JUNGLE_WINE_RACK_SMALL = register("jungle_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block JUNGLE_CABINET = register("jungle_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block JUNGLE_DRAWER = register("jungle_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));


    public static final Block MANGROVE_WINE_RACK_BIG = register("mangrove_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MANGROVE_WINE_RACK_SMALL = register("mangrove_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MANGROVE_CABINET = register("mangrove_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block MANGROVE_DRAWER = register("mangrove_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));

    public static final Block WARPED_WINE_RACK_BIG = register("warped_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block WARPED_WINE_RACK_SMALL = register("warped_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block WARPED_CABINET = register("warped_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block WARPED_DRAWER = register("warped_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));

    public static final Block CRIMSON_WINE_RACK_BIG = register("crimson_wine_rack_big", new NineBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CRIMSON_WINE_RACK_SMALL = register("crimson_wine_rack_small", new FourBottleStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CRIMSON_CABINET = register("crimson_cabinet", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_3_OPEN, VinerySoundEvents.WINE_RACK_3_CLOSE));
    public static final Block CRIMSON_DRAWER = register("crimson_drawer", new WineRackStorageBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), VinerySoundEvents.WINE_RACK_5_OPEN, VinerySoundEvents.WINE_RACK_5_CLOSE));


    public static final Block POTTED_ROSE = registerBlockWithoutItem("potted_rose", new FlowerPotBlock(ObjectRegistry.ROSE, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));


    public static final Identifier NOTE_PAPER_WRITTEN_PACKET_IDENTIFIER = new CandlelightIdentifier("note_paper_written");



    private static <T extends Block> T register(String path, T block) {
        return register(path, block, true);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem) {
        return register(path, block, registerItem, settings -> {
        });
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, Consumer<Item.Settings> consumer) {
        return register(path, block, registerItem, BlockItem::new, consumer);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, BiFunction<T, Item.Settings, ? extends BlockItem> function, Consumer<Item.Settings> consumer) {
        final Identifier id = new CandlelightIdentifier(path);
        BLOCKS.put(id, block);
        if (registerItem) {
            ITEMS.put(id, function.apply(block, getSettings(consumer)));
        }
        return block;
    }


    private static <T extends Block> T registerWine(String path, T block, StatusEffect effect) {
        return register(path, block, true, DrinkBlockItem::new, settings -> settings.food(wineFoodComponent(effect)));
    }

    private static <T extends Block> T registerBigWine(String path, T block, StatusEffect effect) {
        return register(path, block, true, DrinkBlockBigItem::new, settings -> settings.food(wineFoodComponent(effect)));
    }

    private static FoodComponent wineFoodComponent(StatusEffect effect) {
        List<Pair<StatusEffectInstance, Float>> statusEffects = new ArrayList<>();
        statusEffects.add(new Pair<>(new StatusEffectInstance(effect, 45 * 20), 1.0f));
        return new VineryFoodComponent(statusEffects);
    }

    private static <T extends Item> T register(String path, T item) {
        final Identifier id = new CandlelightIdentifier(path);
        ITEMS.put(id, item);
        return item;
    }

    public static void init() {
        for (Map.Entry<Identifier, Block> entry : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Identifier, Item> entry : ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, entry.getKey(), entry.getValue());
        }
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(FLOORBOARD, 5, 20);
    }



    private static Item.Settings getSettings(Consumer<Item.Settings> consumer) {
        Item.Settings settings = new Item.Settings();
        consumer.accept(settings);
        return settings;
    }

    private static Item.Settings getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Block.Settings getBushSettings() {
        return FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH);
    }


    private static AbstractBlock.Settings getGrassSettings() {
        return FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).nonOpaque();
    }

    private static AbstractBlock.Settings getGrapevineSettings() {
        return FabricBlockSettings.of(Material.WOOD).strength(2.0F).ticksRandomly().sounds(BlockSoundGroup.WOOD);
    }

    private static AbstractBlock.Settings getLogBlockSettings() {
        return AbstractBlock.Settings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD);
    }

    private static AbstractBlock.Settings getSlabSettings() {
        return getLogBlockSettings().resistance(3.0F);
    }

    private static AbstractBlock.Settings getWineSettings() {
        return AbstractBlock.Settings.copy(Blocks.GLASS).nonOpaque().breakInstantly();
    }

    public static List<ItemConvertible> getItemConvertibles() {
        List<ItemConvertible> list = new ArrayList<>();
        for (Block entry : BLOCKS.values()) {
            if (entry.asItem() != null) {
                list.add(entry);
            }
        }
        list.addAll(ITEMS.values());
        return list;
    }


    public static Map<Identifier, Block> getBlocks() {
        return Collections.unmodifiableMap(BLOCKS);
    }

    public static Map<Identifier, Item> getItems() {
        return Collections.unmodifiableMap(ITEMS);
    }


    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Candlelight.MOD_ID, name), block);
    }

}
