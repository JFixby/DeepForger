/*
Rebecca-E
    Copyright (C) 2008  by Sergey Stankevich (robotics@icstweb.org),

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.


 */

package rebecca.e10.pipelines;

import java.awt.image.BufferedImage;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.PipeLine;
import rebecca.e10.core.Slot;
import rebecca.e10.pipes.ImageLoader;

public class FileLoaderLine extends PipeLine {

	public FileLoaderLine(Slot<String> path, Slot<BufferedImage> bi) {
		super("Image-File Loader PipeLine");

		Pipe L = new ImageLoader(path, bi);

		this.registerInSlot(path);
		this.registerOutSlot(bi);
		this.registerPipe(L);

	}

}
