-keep class com.blongho.** {*;}
-keep interface com.blongho.**
-keeppackagenames com.blongho.country_data
-keepclassmembers class com.blongho.country_data.* {
   public *;
}
-keep class com.blongho.country_data.R$*{
    *;
 }