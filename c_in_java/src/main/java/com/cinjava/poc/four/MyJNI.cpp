/* MyJNI.cpp : Defines the entry point for the DLL application. */
#include <stdio.h>
#include "MyJNI.h"
BOOL APIENTRY DllMain ( HANDLE hModule,
                        DWORD  ul_reason_for_call,
                        LPVOID lpReserved
                      )
{
    return TRUE;
}

JNIEXPORT void JNICALL Java_com_cinjava_poc_four_MyJNI_HelloWorld (JNIEnv *,
                                              jobject)
{
  printf ("Hello World!\n");

}

JNIEXPORT jint JNICALL Java_com_cinjava_poc_four_MyJNI_CallC (JNIEnv * env,
                                         jobject  jobj,
                                         jboolean z,
                                         jbyte    b,
                                         jchar    c,
                                         jshort   s,
                                         jint     i,
                                         jlong    j,
                                         jfloat   f,
                                         jdouble  d,
                                         jstring  str)
{

  printf ("Arguments from Java \n");
  printf ("z (boolean) = %s\n", b ? "true" : "false");
  printf ("b (byte) = %c\n", b);
  printf ("c (char) = %c\n", c);
  printf ("s (short) = %d\n", (int)s);
  printf ("i (int) = %d\n", i);
  printf ("l (long) = %ld\n",j);
  printf ("f (float) = %f\n", f);
  printf ("d (double) = %f\n", d);
  printf ("str (string) = %s\n", env->GetStringUTFChars( str, NULL));
  return 0;
}

JNIEXPORT void JNICALL Java_com_cinjava_poc_four_MyJNI_SetMembers (JNIEnv * env,
                                              jobject jobj)
{

  jfieldID fidnum;
  printf ("Java members (from C)\n");

   /* Get a reference to this object's class */
   jclass thisclass = env->GetObjectClass(jobj);

  /* ============== Boolean ================= */
  /* Boolean Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "z", "Z");
  if (NULL == fidnum) {
    return;
  }
  /* Get the boolean given the Field ID */
  jboolean z = env->GetBooleanField(jobj, fidnum);
  printf ("z (boolean) = %s\n", z ? "true" : "false");

  /* Change the variable */
  z = false;
  env->SetBooleanField(jobj, fidnum, z);

  /* ============== Byte ================= */
  /* Byte Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "b", "B");
  if (NULL == fidnum) {
    return;
  }
  /* Get the byte given the Field ID */
  jbyte b = env->GetByteField(jobj, fidnum);
  printf ("b (byte) = %c\n", b);

  /* Change the variable */
  b += 1;
  env->SetByteField(jobj, fidnum, b);

  /* ============== Char ================= */
  /* Char Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "c", "C");
  if (NULL == fidnum) {
    return;
  }
  /* Get the char given the Field ID */
  jchar c = env->GetCharField(jobj, fidnum);
  printf ("c (char) = %c\n", c);

  /* Change the variable */
  c += 1;
  env->SetCharField(jobj, fidnum, c);

  /* ============== Short ================= */
  /* Short Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "s", "S");
  if (NULL == fidnum) {
    return;
  }
  /* Get the short given the Field ID */
  jshort s = env->GetShortField(jobj, fidnum);
  printf ("s (short) = %d\n", s);

  /* Change the variable */
  s = 10;
  env->SetShortField(jobj, fidnum, s);

  /* ============== Int ================= */
  /* Int Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "i", "I");
  if (NULL == fidnum) {
    return;
  }
  /* Get the int given the Field ID */
  jint i = env->GetIntField(jobj, fidnum);
  printf ("i (int) = %d\n", i);

  /* Change the variable */
  i = 100;
  env->SetIntField(jobj, fidnum, i);

  /* ============== Long ================= */
  /* Long Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "j", "J");
  if (NULL == fidnum) {
    return;
  }
  /* Get the long given the Field ID */
  jlong j = env->GetLongField(jobj, fidnum);
  printf ("l (long) = %ld\n", j);

  /* Change the variable */
  j = 1000;
  env->SetLongField(jobj, fidnum, j);

  /* ============== Float ================= */
  /* Float Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "f", "F");
  if (NULL == fidnum) {
    return;
  }
  /* Get the float given the Field ID */
  jfloat f = env->GetFloatField(jobj, fidnum);
  printf ("f (float) = %f\n", f);

  /* Change the variable */
  f = (float)1.2;
  env->SetFloatField(jobj, fidnum, f);

  /* ============== Double ================= */
  /* Double Get the Field ID of the instance */
  fidnum = env->GetFieldID(thisclass, "d", "D");
  if (NULL == fidnum) {
    return;
  }
  /* Get the Double given the Field ID */
  jdouble d = env->GetDoubleField(jobj, fidnum);
  printf ("d (double) = %f\n", d);

  /* Change the variable */
  d = 1.5;
  env->SetDoubleField(jobj, fidnum, d);

  /* ============== String ================= */
  /* String Get the Field ID of the instance */
   fidnum = env->GetFieldID(thisclass, "str", "Ljava/lang/String;");
  if (NULL == fidnum) {
    return;
  }
  /* Get the String given the Field ID */
  jstring str = (jstring)env->GetObjectField(jobj, fidnum);
  printf ("str (string) = %s\n", env->GetStringUTFChars(str, NULL));

  /* Change the variable */
  env->ReleaseStringUTFChars(str, "str");
  str = env->NewStringUTF ((const char *) "Hello form C!");
  env->SetObjectField(jobj, fidnum, str);

}