/*
 *
 * Copyright (C) 2013-2014 Brockmann Consult GmbH (info@brockmann-consult.de)
 * Copyright (C) 2014-2015 CS SI
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see http://www.gnu.org/licenses/
 *
 */

package jp2.segments;

import jp2.MarkerSegment;
import jp2.MarkerType;

import javax.imageio.stream.ImageInputStream;
import java.io.IOException;

/**
 * @author Norman Fomferra
 */
public class IgnoredSegment extends MarkerSegment {
    private final int code;

    public IgnoredSegment(int code) {
        super(MarkerType.___);
        this.code = code;
    }

    public IgnoredSegment(MarkerType markerType) {
        super(markerType);
        this.code = 0; // fixme put meaningful code later
    }

    public int getCode() {
        return code;
    }

    public String toHexString() {
        return "0x" + Integer.toHexString(code).toUpperCase();
    }

    @Override
    public void readFrom(ImageInputStream stream) throws IOException {
    }
}