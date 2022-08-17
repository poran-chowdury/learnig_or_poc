#include "stdio.h"
#include "jni.h"
#include "com_cinjava_poc_JavaToC.h"
JNIEXPORT void JNICALL Java_JavaToC_helloC(JNIEnv *env, jobject javaobj)
{
	printf("Hello World: From C");
	return;
}