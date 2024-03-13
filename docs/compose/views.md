# Android Screen Content Views

Our SDK at BotStacks includes convenience screen content views, which are essentially a couple of components working together under the hood for you generally using a `State`.

The views rely on a `State` object for driving UI and handling asynchronous updates for saving, creating, and updating. The `state` will be a required parameter on each View.

They generally can be used for the entire contents on a "screen" and are drop in capable.

Outlined below are the content views currently available and examples on how to use them.

- [ChannelSettingsView](#channelsettingsview)
- [CreateChannelView](#createchannelview)
- [EditProfileView](#editprofileview)
- [SelectChannelUsersView](#selectchannelusersview)
- [UserDetailsView](#userdetailsview)

## ChannelSettingsView

A screen content view for displaying settings and details for a given Chat channel. This relies on the `ChannelSettingsState`.

To persist changes to the channel settings, call `state.update()`.

```kotlin
val state = remember(chat) { ChannelSettingsState(chat) }
ChannelSettingsView(state)
```

For a more detailed API definition, check out the API docs here.

## CreateChannelView

A screen content view for creating a new Channel. This has a callback to trigger navigation to selecting users for the channel. The UI for this screen can be found in [SelectChannelUsersView](#selectchannelusersview).

To create the channel, call `state.create()`.

```kotlin
val state = remember { CreateChannelState() }
CreateChannelView(state) {
    // go to pick users
}
```

For a more detailed API definition, check out the API docs here.

## EditProfileView

A screen content view for editing the current user.

To trigger an update once modifications are done within the view, simply call `state.update()`.

```kotlin
val state = remember { EditProfileState() }
EditProfileView(state)
```

For a more detailed API definition, check out the API docs here.

## SelectChannelUsersView

This is used in coordination with either a [CreateChannelView](views.md#createchannelview) or a [ChannelSettingsView](views.md#channelsettingsview) to set or update the users in a given channel.

```kotlin
val state = remember(chat) { ChannelUserSelectionState(chat) }
SelectChannelUsersView(state = state)
```

For a more detailed API definition, check out the API docs here.

## UserDetailsView

```kotlin
val state = remember(user) { UserDetailsState(user) }
UserDetailsView(state = state)
```

For a more detailed API definition, check out the API docs here.