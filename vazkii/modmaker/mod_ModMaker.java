package vazkii.modmaker;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import net.minecraft.client.Minecraft;
import vazkii.codebase.common.CommonUtils;
import vazkii.modmaker.mod.OreGenerator;
import vazkii.modmaker.tree.objective.UserMod;
import vazkii.um.common.ModConverter;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "modmaker_Vz", name = "Layman Mod Maker", version = "by Vazkii. Version [1.0.5] for 1.3.2.")
public class mod_ModMaker {

	public static TreeMap<String, UserMod> userMods = new TreeMap();
	public static LinkedHashSet<String> claimedSprites = new LinkedHashSet();
	public static File usermodsFile;

	@Init
	public void onInit(FMLInitializationEvent event) {
		usermodsFile = CommonUtils.getSide().isClient() ? Minecraft.getAppDir("minecraft/usermods") : new File(".", "usermods");
		usermodsFile.mkdir();
		try {
			keybindInit();
		} catch (Throwable e) {
		}
		new ModMakerUpdateHandler(ModConverter.getMod(getClass()));
	}

	public void keybindInit() {
		KeyBindingRegistry.registerKeyBinding(new ModMakerKeyHandler());
	}

	@PostInit
	public void onPostInit(FMLPostInitializationEvent event) {
		IOHelper.loadAllMods();
		GameRegistry.registerWorldGenerator(new OreGenerator());
	}

}
