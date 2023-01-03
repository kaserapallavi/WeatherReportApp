# Android MVVM Architecture Sample (ViewModel + LiveData + Kotlin + Retrofit) - Weather App

MVVM Architecture is one of the most popular and latest architecture to develop a maintanable and managable codebase. We are developing a sample `Weater Report for particular city` Android App with `MVVM Architecture` using `Kotlin` language and `Retrofit` network calling library.

### Open Weather API
I have used [Open Weather Map API](https://openweathermap.org/api) for collecting weather information. To get the real weather information of a city, you need to sign up and get your own `APP ID`. Otherwise you can test the API with their sample `BASE URL` and sample `APP ID` without creating account.

### Project Setup
Clone the project and open it using Android Studio. Then open your `local.properties` file under `Gradle Scripts`. You need to specify the `base_url` and `app_id` in your `local.properties` file. Store your API secret in plain string file or Kotlin file is very risky. For security purpose it's better store in local.properties file than plain string/Kotlin file.

#### Use Real APP ID after sign up and activation of your APP ID
After Sign up at the website collect your own `APP ID` from their [API Keys page](https://home.openweathermap.org/api_keys). Then add below lines with your APP ID at the end of `local.properties` file.
```properties
#this is real Base URL
base_url=https://api.openweathermap.org/data/2.5/

#this is real App ID of Open Weather API
app_id=YOUR_OWN_APP_ID
```
The BASE URL and APP ID will be fetched from `build.gradle` file and will be stored it in `BuildConfig`. And `Retrofit` API call will use the BASE URL and APP ID from `BuildConfig`.

### Run the project
Sync the `Gradle` and run the project. Install APK on your emulator or real device. Turn on the internet of your testing device.

