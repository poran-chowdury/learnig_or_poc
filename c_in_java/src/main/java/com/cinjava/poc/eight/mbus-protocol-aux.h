//------------------------------------------------------------------------------
// Copyright (C) 2011, Robert Johansson and contributors, Raditex AB
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

/**
 * @file   mbus-protocol-aux.h
 *
 * @brief  Auxiliary functions to the Freescada libmbus library
 *
 * The idea is to simplify the basic task of querying MBus slaves and
 * the data processing.
 * Typical use might be (in oversimplified "pseudocode"):
 * \verbatim
 * mbus_handle = mbus_context_serial(device);
 *   or
 * mbus_handle = mbus_context_tcp(host, port);
 *
 * mbus_context_set_option(mbus_handle,option,value); // optional
 *
 * mbus_connect(mbus_handle);
 *
 *  ...
 *
 * mbus_read_slave(mbus_handle, addresses, &reply);
 * mbus_frame_data_parse(reply, &frameData);
 * // check the header / record type (fixed/variable)
 *
 * // for fixed use mbus_data_fixed_medium and 2x mbus_parse_fixed_record
 * mbus_data_fixed_medium
 * mbusRecord = mbus_parse_fixed_record() // first record
 *  // process mbusRecord
 * mbusRecord = mbus_parse_fixed_record() // second record
 *  // process mbusRecord
 *
 * // for variable use mbus_parse_variable_record
 * for each record
 *     mbusRecord = mbus_parse_variable_record(record)
 *     // process mbusRecord
 *
 *  ...
 *
 * mbus_disconnect(mbus_handle);
 * mbus_context_free(mbus_handle);
 * \endverbatim
 *
 * Note that the quantity values are partially "normalized". For example energy
 * is in Wh even when originally used "decimal" prefixes like MWh. This seems to
 * be sensible as it make easier any processing of a dataset with huge
 * fluctuation (no need to lookup/convert the prefixes). Also the potential
 * conversion to desired units is pretty easy.
 *
 * On the other hand we acknowledge that use of certain units are expected so we
 * do not convert all units to Si (i.e. no conversion from J to Wh or Bar to Pa.)
 */

#include <stddef.h>
#include <stdint.h>

#ifndef __MBUS_PROTOCOL_AUX_H__
#define __MBUS_PROTOCOL_AUX_H__

#ifdef __cplusplus
extern "C"
{
#endif

#define MBUS_PROBE_NOTHING 0
#define MBUS_PROBE_SINGLE 1
#define MBUS_PROBE_COLLISION 2
#define MBUS_PROBE_ERROR -1

#define MBUS_FRAME_PURGE_S2M 2
#define MBUS_FRAME_PURGE_M2S 1
#define MBUS_FRAME_PURGE_NONE 0

    /**
 * Convert a buffer with hex values into a buffer with binary values.
 *
 * @param dst      destination buffer with binary values
 * @param dst_len  byte count of destination buffer
 * @param src      source buffer with hex values
 * @param src_len  byte count of source buffer
 *
 * @return byte count of successful converted values
 */
    size_t mbus_hex2bin(unsigned char *dst, size_t dst_len, const unsigned char *src, size_t src_len);

#ifdef __cplusplus
}
#endif

#endif // __MBUS_PROTOCOL_AUX_H__