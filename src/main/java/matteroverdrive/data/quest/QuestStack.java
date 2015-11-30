/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.data.quest;

import matteroverdrive.MatterOverdrive;
import matteroverdrive.init.MatterOverdriveItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import java.util.UUID;

/**
 * Created by Simeon on 11/19/2015.
 */
public class QuestStack
{
    private boolean completed;
    private NBTTagCompound tagCompound;
    private UUID giverUniqueID;
    private Entity giver;
    private Quest quest;

    QuestStack(){}

    public QuestStack(Quest quest,Entity giver)
    {
        this.quest = quest;
        if (giver != null)
            this.giverUniqueID = giver.getUniqueID();
        this.giver = giver;
    }

    public QuestStack(Quest quest)
    {
        this.quest = quest;
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {
        if (this.tagCompound != null)
            tagCompound.setTag("Data", this.tagCompound);
        if (giverUniqueID != null)
        {
            tagCompound.setLong("giveIdLow", giverUniqueID.getLeastSignificantBits());
            tagCompound.setLong("giveIdHigh",giverUniqueID.getMostSignificantBits());
        }
        tagCompound.setShort("Quest",(short) MatterOverdrive.quests.getQuestID(quest));
        tagCompound.setBoolean("Completed",completed);
    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {
        if (tagCompound.hasKey("Data", Constants.NBT.TAG_COMPOUND))
            this.tagCompound = tagCompound.getCompoundTag("Data");
        if (tagCompound.hasKey("giveIdLow", Constants.NBT.TAG_LONG) && tagCompound.hasKey("giveIdHigh",Constants.NBT.TAG_LONG))
        {
            giverUniqueID = new UUID(tagCompound.getLong("giveIdLow"),tagCompound.getLong("giveIdHigh"));
        }
        if (tagCompound.hasKey("Quest",Constants.NBT.TAG_SHORT))
        {
            quest = MatterOverdrive.quests.getQuestWithID(tagCompound.getShort("Quest"));
        }
        completed = tagCompound.getBoolean("Completed");
    }

    public static QuestStack loadFromNBT(NBTTagCompound tagCompound)
    {
        QuestStack questStack = new QuestStack();
        questStack.readFromNBT(tagCompound);
        return questStack;
    }

    public String getTitle()
    {
        return quest.getTitle(this);
    }

    public String getTitle(EntityPlayer entityPlayer)
    {
        return quest.getTitle(this,entityPlayer);
    }

    public String getInfo(EntityPlayer entityPlayer)
    {
        return quest.getInfo(this,entityPlayer);
    }

    public String getObjective(EntityPlayer entityPlayer,int objectiveIndex)
    {
        return quest.getObjective(this,entityPlayer,objectiveIndex);
    }

    public int getObjectivesCount(EntityPlayer entityPlayer)
    {
        return quest.getObjectivesCount(this,entityPlayer);
    }

    public boolean isObjectiveCompleted(EntityPlayer entityPlayer,int objectiveID)
    {
        return quest.isObjectiveCompleted(this,entityPlayer,objectiveID);
    }

    public Entity getGiver()
    {
        return giver;
    }

    public boolean isGiver(Entity entity)
    {
        if (giver != null && giver == entity)
        {
            return true;
        }
        if (giverUniqueID != null && entity.getUniqueID().equals(giverUniqueID))
        {
            return true;
        }
        return false;
    }

    public boolean hasGiver()
    {
        if (getGiver() != null)
        {
            return true;
        }
        if (giverUniqueID != null)
        {
            return true;
        }
        return false;
    }

    public void setGiver(Entity entity)
    {
        this.giver = entity;
        this.giverUniqueID = giver.getUniqueID();
    }

    public Quest getQuest()
    {
        return quest;
    }

    public NBTTagCompound getTagCompound()
    {
        return tagCompound;
    }

    public void setTagCompound(NBTTagCompound tagCompound)
    {
        this.tagCompound = tagCompound;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public QuestStack copy()
    {
        QuestStack questStack = new QuestStack(this.quest);
        questStack.giverUniqueID = giverUniqueID;
        questStack.giver = giver;
        if (getTagCompound() != null)
        {
            questStack.setTagCompound((NBTTagCompound)getTagCompound().copy());
        }
        return questStack;
    }

    public ItemStack getContract()
    {
        ItemStack contract = new ItemStack(MatterOverdriveItems.contract);
        NBTTagCompound questTag = new NBTTagCompound();
        writeToNBT(questTag);
        contract.setTagCompound(questTag);
        return contract;
    }

    public static boolean canComplete(EntityPlayer entityPlayer,QuestStack questStack)
    {
        for (int i = 0;i < questStack.getObjectivesCount(entityPlayer);i++)
        {
            if (!questStack.isObjectiveCompleted(entityPlayer,i))
            {
                return false;
            }
        }
        return true;
    }
}