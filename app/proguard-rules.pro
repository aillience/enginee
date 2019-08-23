# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android-studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

#---------------------------------1.实体类---------------------------------
-keep class Model.** { *; }
-ignorewarnings

-keep class org.apache.tika.** { *; }
-dontwarn org.apache.tika.**
-keep class okhttp3.internal.** { *; }
-dontwarn okhttp3.internal.**
#-------------------------------------------------------------------------
#---------------------------------2.第三方包-------------------------------

#eventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
#-keepnames class com.bangbangli.utils.image.GlideCache
#由于AppGlideModule原因，打包时异常，添加下面混淆
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl { *; }
#glide如果你的API级别<=Android API 27 则需要添加
#-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
