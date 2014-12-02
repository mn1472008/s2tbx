/*
 * Copyright (C) 2014 by Array Systems Computing Inc. http://www.array.ca
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see http://www.gnu.org/licenses/
 */
package org.esa.nest.dat.actions;

import org.esa.beam.framework.ui.command.CommandEvent;
import org.esa.beam.framework.ui.command.ExecCommand;

/**
 * This does nothing
 *
 * @author lveci
 * @version $Revision: 1.5 $ $Date: 2011-04-08 18:23:59 $
 */
public class ComingSoonAction extends ExecCommand {

    @Override
    public void actionPerformed(final CommandEvent event) {

    }

    @Override
    public void updateState(final CommandEvent event) {
        setEnabled(false);
    }
}