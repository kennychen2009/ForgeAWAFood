
package me.kuan.awasnack.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import me.kuan.awasnack.AwaSnackModElements;

@AwaSnackModElements.ModElement.Tag
public class AWAsnackItemGroup extends AwaSnackModElements.ModElement {
	public AWAsnackItemGroup(AwaSnackModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabaw_asnack") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Items.BAKED_POTATO);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
