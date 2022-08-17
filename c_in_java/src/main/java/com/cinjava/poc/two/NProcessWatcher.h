#include <native.h>

/* header for class NProcessWatcher */
#ifndef _Included_NProcessWatcher
#define _Included_NProcessWatcher
#ifdef __cplusplus
extern "C" {
#endif
typedef struct ClassNProcessWatcher {
  char pad[1];  /* Padding for ANSI C */
} ClassNProcessWatcher;

HandleTo(NProcessWatcher);

extern void NProcessWatcher_nativeGetProcessInfo
              (struct HNProcessWatcher*);
extern long NProcessWatcher_nativeGetNumElements
              (struct HNProcessWatcher*);
extern long NProcessWatcher_nativeGetCurrentSize
              (struct HNProcessWatcher*, long);
extern long NProcessWatcher_nativeGetProcessSize
              (struct HNProcessWatcher*, long);
extern long NProcessWatcher_nativeGetProcessNameLength
              (struct HNProcessWatcher*, long);
extern /*unicode*/ long
              NProcessWatcher_nativeGetProcessNameChar
              (struct HNProcessWatcher*, long, long);
#ifdef __cplusplus
}
#endif

#endif /* _Included_NProcessWatcher */