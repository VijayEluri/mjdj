/*
Mjdj MIDI Morph - an extensible MIDI processor and translator.
Copyright (C) 2010 Confusionists, LLC (www.confusionists.com)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 

You may contact the author at mjdj_midi_morph [at] confusionists.com
*/
package com.confusionists.mjdj.fileIO;

import java.io.File;

import com.confusionists.mjdj.Main;
import com.confusionists.mjdj.ui.Logger;

public class ExternalDirectories {
	private static String DEFAULT_MORPHS_PATH = "morphs";
	private static String DEFAULT_MORPHS_GROOVY_PATH = "morphs-groovy";
	private static String DEFAULT_DEVICES_PATH = "devices";
	public File morphsDir = null;
	public File devicesDir = null;
	public File morphsCompileDir;
	public File morphsDirGroovy;
	public static ExternalDirectories instance = null;


	/* throws IOException with any problems */
	@Deprecated
	public static void init(String morphsPath) throws Exception {}

   static {
		instance = new ExternalDirectories();
	}
   
   public static String getUserDirectory() {
	   if (Main.isDevelopment)
		   return ".";
	   else 
		   return System.getProperty("user.home") + "/MJDJ";
   }
   
   public static String getUserDirectory(String path) {
	   return getUserDirectory() + "/" + path;
   }
   
   
   private ExternalDirectories() {
	   super();
	   morphsDir = checkDir(DEFAULT_MORPHS_PATH);
	   morphsDirGroovy = checkDir(DEFAULT_MORPHS_GROOVY_PATH);
	   devicesDir = checkDir(DEFAULT_DEVICES_PATH);
   }
   
	private File checkDir(String dirName) {
		dirName = getUserDirectory(dirName);
		File retVal = new File(dirName);
		if (retVal.exists()) {
			if (!retVal.isDirectory()) {
				throw new RuntimeException("Path " + retVal.getAbsolutePath() + " is not a directory.");
			} else 
				Logger.log("Found: " + retVal.getAbsolutePath());
		} else {
			if (retVal.mkdirs()) {
				Logger.log("Sucessfully made directory " + retVal.getAbsolutePath() + ".");
			} else {
				throw new RuntimeException("Could not create directory " + retVal.getAbsolutePath() + ".");
			}
		}
		return retVal;
	}

}
