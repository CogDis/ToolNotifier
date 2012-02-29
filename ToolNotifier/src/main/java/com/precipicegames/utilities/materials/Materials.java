package com.precipicegames.utilities.materials;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Material;

public class Materials {
	HashMap<String, Material> materials = new HashMap<String, Material>();

	public Materials() {
		this.materials.put("air", Material.AIR);
		this.materials.put("apple", Material.APPLE);
		this.materials.put("arrow", Material.ARROW);
		this.materials.put("bed", Material.BED);
		this.materials.put("bedrock", Material.BEDROCK);
		this.materials.put("bed block", Material.BED_BLOCK);
		this.materials.put("boat", Material.BOAT);
		this.materials.put("bone", Material.BONE);
		this.materials.put("book", Material.BOOK);
		this.materials.put("bookshelf", Material.BOOKSHELF);
	}

	public Material matchMaterial(String material) {
		Material match = null;
		int currLen = 0;
		int lastLen = 0;
		for (Iterator<String> i$ = this.materials.keySet().iterator(); i$.hasNext(); ) { Object key = i$.next();
		currLen = longestSubstr(material, (String)key);
		if (currLen > lastLen) {
			match = (Material)this.materials.get(key);
		}
		lastLen = currLen;
		}
		return match;
	}

	private static int longestSubstr(String first, String second) {
		if ((first == null) || (second == null) || (first.length() == 0) || (second.length() == 0)) {
			return 0;
		}

		int maxLen = 0;
		int fl = first.length();
		int sl = second.length();
		int[][] table = new int[fl][sl];

		for (int i = 0; i < fl; i++) {
			for (int j = 0; j < sl; j++) {
				if (first.charAt(i) == second.charAt(j)) {
					if ((i == 0) || (j == 0)) {
						table[i][j] = 1;
					}
					else {
						table[i][j] = (table[(i - 1)][(j - 1)] + 1);
					}
					if (table[i][j] > maxLen) {
						maxLen = table[i][j];
					}
				}
			}
		}
		return maxLen;
	}
}
