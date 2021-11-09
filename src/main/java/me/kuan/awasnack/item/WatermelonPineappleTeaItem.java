
package me.kuan.awasnack.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;

import me.kuan.awasnack.procedures.WmpatFoodEatenProcedure;
import me.kuan.awasnack.itemgroup.AWAsnackItemGroup;
import me.kuan.awasnack.AwaSnackModElements;

import java.util.Map;
import java.util.HashMap;

@AwaSnackModElements.ModElement.Tag
public class WatermelonPineappleTeaItem extends AwaSnackModElements.ModElement {
	@ObjectHolder("awa_snack:watermelon_pineapple_tea")
	public static final Item block = null;
	public WatermelonPineappleTeaItem(AwaSnackModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(AWAsnackItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE)
					.food((new Food.Builder()).hunger(20).saturation(1f).setAlwaysEdible().build()));
			setRegistryName("watermelon_pineapple_tea");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = new ItemStack(Items.GLASS_BOTTLE);
			super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				WmpatFoodEatenProcedure.executeProcedure($_dependencies);
			}
			if (itemstack.isEmpty()) {
				return retval;
			} else {
				if (entity instanceof PlayerEntity) {
					PlayerEntity player = (PlayerEntity) entity;
					if (!player.isCreative() && !player.inventory.addItemStackToInventory(retval))
						player.dropItem(retval, false);
				}
				return itemstack;
			}
		}
	}
}
