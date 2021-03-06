package com.nicky.grisha.registry;

import com.nicky.grisha.Grisha;
import com.nicky.grisha.items.BagItem;
import com.nicky.grisha.items.JurdaParemItem;
import com.nicky.grisha.status_effects.JurdaParem;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GrishaItems {

	//Items
	public static final Item CORE_CLOTH = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item PEBBLE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	
	//Block Items
	public static final BlockItem PEBBLE_BLOCK = new BlockItem(GrishaBlocks.PEBBLE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final BlockItem WILD_JURDA_ITEM = new BlockItem(GrishaBlocks.WILD_JURDA_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));

	//Armor
	//Kefta Purple
	  public static final ArmorMaterial KEFTA_PURPLE_MATERIAL = new GrishaArmor(1);
      public static final Item KEFTA_PURPLE_HOOD = new ArmorItem(KEFTA_PURPLE_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_PURPLE_CHESTPLATE = new ArmorItem(KEFTA_PURPLE_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_PURPLE_LEGGINGS = new ArmorItem(KEFTA_PURPLE_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_PURPLE_BOOTS = new ArmorItem(KEFTA_PURPLE_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
	
    //Kefta Purple
      public static final ArmorMaterial KEFTA_RED_MATERIAL = new GrishaArmor(2);
      public static final Item KEFTA_RED_HOOD = new ArmorItem(KEFTA_RED_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_RED_CHESTPLATE = new ArmorItem(KEFTA_RED_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_RED_LEGGINGS = new ArmorItem(KEFTA_RED_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_RED_BOOTS = new ArmorItem(KEFTA_RED_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    //Kefta Purple
      public static final ArmorMaterial KEFTA_BLUE_MATERIAL = new GrishaArmor(3);
      public static final Item KEFTA_BLUE_HOOD = new ArmorItem(KEFTA_BLUE_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_BLUE_CHESTPLATE = new ArmorItem(KEFTA_BLUE_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_BLUE_LEGGINGS = new ArmorItem(KEFTA_BLUE_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
      public static final Item KEFTA_BLUE_BOOTS = new ArmorItem(KEFTA_BLUE_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

      
      //Jurda Seed Item 
      public static final Item JURDA_SEEDS = new AliasedBlockItem(GrishaBlocks.JURDA_BLOCK, new Item.Settings()
    		  .group(ItemGroup.MISC)
    		  .food(new FoodComponent.Builder()
    				  .hunger(1)
    				  .saturationModifier(2f)
    				  .snack()
    				  .alwaysEdible()
    				  .statusEffect(new StatusEffectInstance(StatusEffects.HASTE,200),0.80f)
    				  .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,100),0.80f)
    				  .build()));
      
      //Jurda Parem Items
      public static final Item REFINED_JURDA = new Item(new Item.Settings().group(ItemGroup.MISC));
      public static final Item JURDA_PRIME = new Item(new Item.Settings().group(ItemGroup.MISC));
      public static final JurdaParemItem JURDA_PAREM = new JurdaParemItem(new Item.Settings()
    		  .group(ItemGroup.MISC)
    		  .food(new FoodComponent.Builder()
    				  .hunger(-2)
    				  .saturationModifier(-10f)
    				  .snack()
    				  .alwaysEdible()
    				  .statusEffect(new StatusEffectInstance(Grisha.JURDA_PAREM_EFFECT,JurdaParem.LENGTH,0,false,true),1f)
    				  .build()));
      
      public static final Item CLOTH_BAG = new BagItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));

	public static void registerItems() {
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "core_cloth"), CORE_CLOTH);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "pebble"), PEBBLE);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "pebble_block"), PEBBLE_BLOCK);
		
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_purple_hood"), KEFTA_PURPLE_HOOD);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_purple_chestplate"), KEFTA_PURPLE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_purple_leggings"), KEFTA_PURPLE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_purple_boots"), KEFTA_PURPLE_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_red_hood"), KEFTA_RED_HOOD);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_red_chestplate"), KEFTA_RED_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_red_leggings"), KEFTA_RED_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_red_boots"), KEFTA_RED_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_blue_hood"), KEFTA_BLUE_HOOD);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_blue_chestplate"), KEFTA_BLUE_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_blue_leggings"), KEFTA_BLUE_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "kefta_blue_boots"), KEFTA_BLUE_BOOTS);

		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID,"wild_jurda_item"), WILD_JURDA_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID,"jurda_seeds"), JURDA_SEEDS);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID,"refined_jurda"), REFINED_JURDA);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID,"jurda_prime"), JURDA_PRIME);
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID,"jurda_parem"), JURDA_PAREM);
		
		Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "cloth_bag"), CLOTH_BAG);
		
		//Registry.register(Registry.ITEM, new Identifier(Grisha.MOD_ID, "packed_snowball"), PackedSnowballItem);
	}
}
