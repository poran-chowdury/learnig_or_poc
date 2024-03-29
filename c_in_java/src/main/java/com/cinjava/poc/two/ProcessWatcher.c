//  Implementation of a native shared library for MacOS.
//  Adapted from the example provided with the JDK v1.0.2.

//  OS Headers
#include <CodeFragments.h>

//  JavaH generated Header
#include "NProcessWatcher.h"

//  Our library header.
#include "ProcessWatcher.h"

//  The first functions listed are the calls into our native library. They match the
//  prototypes in the header file NProcessWatcher.h, which we included above.

//  The Java data types (int and char) expected as return types by NProcessWatcher.java
//  map to the long int data type on the Macintosh. We didn't arbitrarily select this; it
//  was generated by JavaH as part of NProcessWatcher.h

//  We have functions calling functions to get the process data.
//  This allows us to change the underlying implementation if necessary.

void NProcessWatcher_nativeGetProcessInfo
              (struct HNProcessWatcher* self)
{
  DoGetProcessInfo();
}

long NProcessWatcher_nativeGetNumElements
              (struct HNProcessWatcher* self)
{
  return( DoGetNumRecords() );
}

long NProcessWatcher_nativeGetCurrentSize
              (struct HNProcessWatcher* self, long i)
{
  return( DoGetCurrentSize( i ) );
}

long NProcessWatcher_nativeGetProcessSize
              (struct HNProcessWatcher* self, long i)
{
  return( DoGetProcessSize( i ) );
}

long NProcessWatcher_nativeGetProcessNameLength
              (struct HNProcessWatcher*, long i)
{
  return( DoGetProcessNameLength( i ) );
}

long NProcessWatcher_nativeGetProcessNameChar
              (struct HNProcessWatcher*, long i, long j)
{
  return( DoGetProcessNameChar( i, j ) );
}

/**************************
  DoGetNumRecords
**************************/

long    DoGetNumRecords( void )
{
  return( ( long )gNumRecords );
}

/**************************
  DoGetCurrentSize
**************************/

long    DoGetCurrentSize( long i )
{
  return( gArray[ i ].currentSize );
}

/**************************
  DoGetProcessSize
**************************/

long    DoGetProcessSize( long i )
{
  return( gArray[ i ].processSize );
}

/**************************
  DoGetProcessNameLength
**************************/

long    DoGetProcessNameLength( long i )
{
  return( gArray[ i ].processName[ 0 ] );
}

/**************************
  DoGetProcessNameChar
**************************/

long    DoGetProcessNameChar( long i, long j )
{
  return( gArray[ i ].processName[ j ] );
}

/**************************
  DoGetProcessInfo
**************************/

//  This routine walks through the current process list
//  and retrieves information we need.
void    DoGetProcessInfo( void )
{
  FSSpec              theSpec;
  OSErr              theError;
  ProcessInfoRec      theInfoRec;
  ProcessSerialNumber  thePSN;
  Str32              theName;

  //  Reset the array count.
  gNumRecords = 0;

  //  Setup the record for retrieving process info.
  theInfoRec.processInfoLength = sizeof( ProcessInfoRec );
  theInfoRec.processName = theName;
  theInfoRec.processAppSpec = &theSpec;

  thePSN.highLongOfPSN = NIL;
  thePSN.lowLongOfPSN = kNoProcess;

  //  Retrieve process info until no more processes are found.
  while ( GetNextProcess( &thePSN ) == noErr )
  {
    theError = GetProcessInformation( &thePSN, &theInfoRec );

    if ( theError != noErr )
      break;

    //  Save the process data into the global array. Start with the process name.
    BlockMove( ( Ptr )theInfoRec.processName,
      ( Ptr )gArray[ gNumRecords ].processName,
      STRING_LENGTH );

    //  Partition sizes are 16 bytes off when compared to "About..."
    //  Current sizes are 15 bytes off when compared to "About..."
    gArray[ gNumRecords ].processSize =
      ( theInfoRec.processSize / ONE_KBYTE_SIZE )
      - DIFFERENCE_FACTOR;
    gArray[ gNumRecords ].currentSize = (
      ( theInfoRec.processSize - theInfoRec.processFreeMem )
      / ONE_KBYTE_SIZE ) - DIFFERENCE_FACTOR + 1;

    gNumRecords++;

    //  Bounds checking.
    if ( gNumRecords > MAX_NUM_RECORDS )
      break;
  }
}

//============================================================

///**************************
//  DoGetNumRecords
//**************************/
//long    DoGetNumRecords( void )
//{
//  return( ( long )gNumRecords );
//}
//
///**************************
//  DoGetCurrentSize
//**************************/
//long    DoGetCurrentSize( long i )
//{
//  return( gArray[ i ].currentSize );
//}
///**************************
//  DoGetProcessSize
//**************************/
//long    DoGetProcessSize( long i )
//{
//  return( gArray[ i ].processSize );
//}
//
///**************************
//  DoGetProcessNameLength
//**************************/
//long    DoGetProcessNameLength( long i )
//{
//  return( gArray[ i ].processName[ 0 ] );
//}
//
///**************************
//  DoGetProcessNameChar
//**************************/
//long    DoGetProcessNameChar( long i, long j )
//{
//  return( gArray[ i ].processName[ j ] );
//}
//
///**************************
//  DoGetProcessInfo
//**************************/
////  This routine walks through the current process list
////  and retrieves information we need.
//void    DoGetProcessInfo( void )
//{
//  FSSpec              theSpec;
//  OSErr              theError;
//  ProcessInfoRec      theInfoRec;
//  ProcessSerialNumber  thePSN;
//  Str32              theName;
//
//  //  Reset the array count.
//  gNumRecords = 0;
//
//  //  Setup the record for retrieving process info.
//  theInfoRec.processInfoLength = sizeof( ProcessInfoRec );
//  theInfoRec.processName = theName;
//  theInfoRec.processAppSpec = &theSpec;
//
//  thePSN.highLongOfPSN = NIL;
//  thePSN.lowLongOfPSN = kNoProcess;
//
//  //  Retrieve process info until no more processes are found.
//  while ( GetNextProcess( &thePSN ) == noErr )
//  {
//    theError = GetProcessInformation( &thePSN, &theInfoRec );
//
//    if ( theError != noErr )
//      break;
//
//    //  Save the process data into the global array. Start with the process name.
//    BlockMove( ( Ptr )theInfoRec.processName,
//      ( Ptr )gArray[ gNumRecords ].processName,
//      STRING_LENGTH );
//
//  //  Partition sizes are 16 bytes off when compared to "About This Macintosh".
//  //  Current sizes are 15 bytes off when compared to "About This Macintosh".
//  //  Adjust and store the resulting values so our display matches the system display.
//    gArray[ gNumRecords ].processSize =
//      ( theInfoRec.processSize / ONE_KBYTE_SIZE )
//      - DIFFERENCE_FACTOR;
//    gArray[ gNumRecords ].currentSize = (
//      ( theInfoRec.processSize - theInfoRec.processFreeMem )
//      / ONE_KBYTE_SIZE ) - DIFFERENCE_FACTOR + 1;
//
//    gNumRecords++;
//
//    //  Bounds checking.
//    if ( gNumRecords > MAX_NUM_RECORDS )
//      break;
//  }
//}


