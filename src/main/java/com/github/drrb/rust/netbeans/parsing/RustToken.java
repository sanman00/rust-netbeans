/*
 * Copyright (C) 2015 drrb
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.drrb.rust.netbeans.parsing;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class RustToken extends Structure {

    public static class ByValue extends RustToken implements Structure.ByValue {

        boolean isEof() {
            return getType() == RustTokenId.EOF;
        }
    }

    public int startLine;
    public int startCol;
    public int endLine;
    public int endCol;
    public int type;

    public RustTokenId getType() {
        return RustTokenId.values()[type];
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("startLine", "startCol", "endLine", "endCol", "type");
    }

    @Override
    public String toString() {
        return String.format("%s: %s,%s-%s,%s%n%s", getType(), startLine, startCol, endLine, endCol, super.toString(true));
    }
}