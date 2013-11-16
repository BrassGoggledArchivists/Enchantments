package com.arcane.mod.common;

import java.io.InputStream;
import java.net.URL;
import java.util.EnumSet;
import java.util.Properties;

import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class UpdateHandler implements ITickHandler
{
    private boolean checkUpdate = true;
    String mod;
    String version;

    public UpdateHandler(String s1, String s2)
    {
        this.mod = s1;
        this.version = s2;
    }

    public static boolean checkForNewVersion(String s1, String s2)
    {
        try
        {
            URL url = new URL("https://dl.dropboxusercontent.com/u/40454999/version.txt");
            InputStream stream = url.openStream();
            Properties prop = new Properties();
            prop.load(stream);
            String s_prop = prop.getProperty(s1);

            if(s2.compareTo(s_prop) != 0)
            {
                return true;
            }
        } catch(Exception e)
        {
            ;
        }

        return false;
    }

    public void tickStart(EnumSet<TickType> set, Object ... obj)
    {
        if(this.checkUpdate)
        {
            if(checkForNewVersion(this.mod, this.version))
            {
                MinecraftServer.getServer().logInfo("There is an available update for " + this.mod + ".");
            }

            this.checkUpdate = false;
        }
    }

    public void tickEnd(EnumSet<TickType> set, Object ... obj) {}

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    public String getLabel()
    {
        return "Update Handler";
    }
}