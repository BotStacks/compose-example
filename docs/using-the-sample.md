### Using the Sample

To use the sample app you will need to obtain your API key from the [Botstacks Dashboard](https://dashboard.botstacks.ai/settings). If you don’t yet have one, you can [create one for FREE](https://dashboard.botstacks.ai/)!

Once you have your key update `botstacks_api_key` in [keys.xml](../composeApp/src/androidMain/res/values/keys.xml) for Android and in [AppSecrets.plist](../iosApp/AppSecrets.plist) for iOS.

If you want Giphy support and/or Google Maps rendering support you will need API keys for those services as well.

#### [Giphy](https://developers.giphy.com/)
A Giphy API key can be added to the above locations as well.

#### Google Maps
[Android](https://developers.google.com/maps/documentation/android-sdk/get-api-key)
[iOS](https://developers.google.com/maps/documentation/ios-sdk/config)

A Google Maps API key can be added to `keys.xml` for Android. For iOS see https://developers.google.com/maps/documentation/ios-sdk/config#get-key.

To enable location sharing on iOS you also need to add privacy reasons here in your [Info.plist.](https://developer.apple.com/documentation/corelocation/requesting_authorization_to_use_location_services#3385302)


## Camera Usage
To suport "Take a Picture" functionality in a conversation you must also notate your NSCameraUsageDescription permission in Info.plist as well.

### [Lets Go](introduction.md)