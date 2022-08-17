/**************************
  ProcessWatcher.h
**************************/
//  Define some macros to make our code more readable.
#define  NIL                0L
#define  ONE_KBYTE_SIZE      1024
#define  DIFFERENCE_FACTOR    16
#define  MAX_NUM_RECORDS      20
#define  STRING_LENGTH        32

//  Define a struct that holds the information we want for
//  one process. Then create an array of these structs.
struct appStruct
{
  Str32  processName;
  long    processSize;
  long    currentSize;
}  ;

struct    appStruct  gArray[ MAX_NUM_RECORDS ];

//  One global variable as an array counter.
short    gNumRecords;

//  Our function prototypes.
long    DoGetNumRecords( void );
long    DoGetCurrentSize( long i );
long    DoGetProcessSize( long i );
long    DoGetProcessNameLength( long i );
long    DoGetProcessNameChar( long i, long j );
void    DoGetProcessInfo( void );