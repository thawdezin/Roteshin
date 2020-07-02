# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.* { *; }
-keep interface com.squareup.okhttp.* { *; }
-dontwarn com.squareup.okhttp.**


# Retrofit
-keep class com.google.gson.* { *; }
-keep public class com.google.gson.* {public private protected *;}
-keep class com.google.inject.* { *; }
-keep class org.apache.http.* { *; }
-keep class org.apache.james.mime4j.* { *; }
-keep class javax.inject.* { *; }
-keep class javax.xml.stream.* { *; }
-keep class retrofit.* { *; }
-keep class com.google.appengine.* { *; }
-keep class retrofit2.* { *; }

-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes EnclosingMethod

-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.PlatformIOSMainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.PlatformJava8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions


# Add any classes the interact with gson
# the following line is for illustration purposes
-keep class com.thawdezin.roteshin.rest.RestClient
-keep class com.thawdezin.roteshin.model.* { *; }
-keep class com.activeandroid.* { *; }
-keep class com.activeandroid.**.* { *; }

# Hide warnings about references to newer platforms in the library
-dontwarn android.support.v7.**
# don't process support library
-keep class android.support.v7.* { *; }
-keep interface android.support.v7.* { *; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider


# To support Enum type of class members
-keepclassmembers enum * { *; }



