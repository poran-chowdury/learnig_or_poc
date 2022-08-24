//------------------------------------------------------------------------------
// Copyright (C) 2010-2011, Robert Johansson and contributors, Raditex AB
// All rights reserved.
//
// rSCADA
// http://www.rSCADA.se
// info@rscada.se
//
// Contributors:
// Large parts of this file was contributed by Tomas Menzl.
//
//------------------------------------------------------------------------------

#include "mbus-protocol-aux.h"
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <stdlib.h>

size_t
mbus_hex2bin(unsigned char *dst, size_t dst_len, const unsigned char *src, size_t src_len)
{
    size_t i, result = 0;
    unsigned long val;
    unsigned char *ptr, *end, buf[3];

    if (!src || !dst)
    {
        return 0;
    }

    memset(buf, 0, sizeof(buf));
    memset(dst, 0, dst_len);

    for (i = 0; i + 1 < src_len; i++)
    {
        // ignore whitespace
        if (isspace(src[i]))
            continue;

        buf[0] = src[i];
        buf[1] = src[++i];

        end = buf;
        ptr = end;
        val = strtoul(ptr, (char **)&end, 16);

        // abort at non hex value
        if (ptr == end)
            break;

        // abort at end of buffer
        if (result >= dst_len)
            break;

        dst[result++] = (unsigned char)val;
    }

    return result;
}