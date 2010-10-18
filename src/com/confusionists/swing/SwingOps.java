/*
Mjdj MIDI Morph - an extensible MIDI processor and translator.
Copyright (C) 2010 Confusionists, LLC (www.confusionists.com)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 

Linking this library statically or dynamically with other modules is making a combined work based on this library. Thus, the terms and conditions of the GNU General Public License cover the whole combination. 

As a special exception, the copyright holders of this library give you permission to link this library with independent modules to produce an executable, regardless of the license terms of these independent modules, and to copy and distribute the resulting executable under terms of your choice, provided that you also meet, for each linked independent module, the terms and conditions of the license of that module. An independent module is a module which is not derived from or based on this library. If you modify this library, you may extend this exception to your version of the library, but you are not obligated to do so. If you do not wish to do so, delete this exception statement from your version. 

You may contact the author at mjdj_midi_morph [at] confusionists.com
*/
package com.confusionists.swing;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.UIManager;

public class SwingOps {

  /**
   * Calls methods setMax, setMin, setPreferred and setSize
   * on a JComponent.
   * @param comp
   * @param dimension
   */
  public static void setAllSizes(Container comp, Dimension dimension) {
    comp.setMaximumSize(dimension);
    comp.setMinimumSize(dimension);
    comp.setPreferredSize(dimension);
    comp.setSize(dimension);
  }

  /**
   * @param component
   * @param width
   * @param height
   */
  public static void setAllSizes(Container container, int width, int height) {
    setAllSizes(container, new Dimension(width, height));
  }

  public static void setLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      
    }
    catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}