package us.skyywastaken.thepowerofthecosmos.bypass;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import us.skyywastaken.thepowerofthecosmos.util.DamageSourceCosmosSword;

public class MultiArmorBypass {
    public void onLivingAttacked(LivingAttackEvent attackEvent) {
        Entity attackingEntity = attackEvent.getSource().getTrueSource();
        Entity target = attackEvent.getEntity();
        if(!(target instanceof EntityPlayer) || !(attackingEntity instanceof EntityPlayer)){
            return;
        }

        EntityPlayer targetPlayer = (EntityPlayer) target;
        EntityPlayer attackPlayer = (EntityPlayer) attackingEntity;
        Item attackItem = attackPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem();
        if(itemIsCosmosSword(attackItem) && playerHasMultiArmor(targetPlayer) && attackEvent.getAmount() < Float.MAX_VALUE){
            attackEvent.setCanceled(true);
            DamageSource damageSource = getCosmosDamageSource(attackPlayer);
            targetPlayer.attackEntityFrom(damageSource, Float.MAX_VALUE);
        }
    }

    private boolean playerHasMultiArmor(EntityPlayer passedPlayer) {
        Item playerHelmet = passedPlayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
        Item playerChestplate = passedPlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        Item playerLeggings = passedPlayer.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem();
        Item playerBoots = passedPlayer.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();

        Item multiHelmet = Item.getByNameOrId("overloaded:multi_helmet");
        Item multiChestplate = Item.getByNameOrId("overloaded:multi_chestplate");
        Item multiLeggings = Item.getByNameOrId("overloaded:multi_leggings");
        Item multiBoots = Item.getByNameOrId("overloaded:multi_boots");

        if(playerHelmet == multiHelmet
                && playerChestplate == multiChestplate
                && playerLeggings == multiLeggings
                && playerBoots == multiBoots) {
            return true;
        } else {
            return false;
        }
    }

    private boolean itemIsCosmosSword(Item passedItem) {
        Item swordOfTheCosmos = Item.getByNameOrId("avaritia:infinity_sword");
        return passedItem == swordOfTheCosmos;
    }

    private DamageSource getCosmosDamageSource(Entity playerSource){
        DamageSource returnSource = new DamageSourceCosmosSword(playerSource);
        returnSource.setDamageIsAbsolute();
        returnSource.setDamageBypassesArmor();
        return returnSource;
    }
}
