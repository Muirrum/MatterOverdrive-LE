/*
 * This file is part of MatterOverdrive: Legacy Edition
 * Copyright (C) 2020, Simeon Radivoev/Horizon Studios <contact@hrznstudio.com>, All rights reserved.
 *
 * MatterOverdrive: Legacy Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MatterOverdrive: Legacy Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.items.weapon.module;

import matteroverdrive.Reference;
import matteroverdrive.api.weapon.WeaponStats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WeaponModuleRicochet extends WeaponModuleBase {
    public WeaponModuleRicochet(String name) {
        super(name);
        applySlot(Reference.MODULE_OTHER);
        applyWeaponStat(0, WeaponStats.RICOCHET, 1);
    }

    @Override
    public String getModelPath() {
        return null;
    }

    @Override
    public ResourceLocation getModelTexture(ItemStack module) {
        return null;
    }

    @Override
    public String getModelName(ItemStack module) {
        return null;
    }
}
