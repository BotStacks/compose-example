# Android Components

Our SDK at BotStacks also allows for direct component usage in your Jetpack Compose applications.

Outlined below are the components currently available and examples on how to use them.

- [Avatar](#avatar)
- [Badge](#badge)
- [ChannelGroup](#channelgroup)
- [ChannelRow](#channelrow)
- [ChatInput](#chatinput)
- [ChatList](#chatlist)
- [ChatMessage](#chatmessage)
- [ChatMessagePreview](#chatmessagepreview)
- [Header](#header)
- [MediaActionSheet](#mediaactionsheet)
- [MessageActionSheet](#messageactionsheet)
- [MessageList](#messagelist)
- [Spinner](#spinner)
- [UserProfile](#userprofile)
- [UserSelect](#userselect)

## Avatar

Our Avatar component renders an image for a given URL in a bordered circle at the specified size. This is utilized in our larger components to render user Avatars (as the component name claims).

There are a few overload methods for this component dependent on your usage.


```kotlin
Avatar(user = message.user)
Avatar(url = chat.displayImage, size = AvatarSize.Large, chat = true)
Avatar(type = AvatarType.Channel(listOf(chat.displayImage)))
Avatar(type = AvatarType.User(user))
```
Avatar sizing resolves around two defined sizes, with the ability to define a custom size.

`AvatarSize.Small` - renders at 50 dp

`AvatarSize.Large` - renders at 100 dp

`AvatarSize.Custom(val dp: Dp)` - renders at specified size

For a more detailed API definition, check out the API docs here.

## Badge

Our Badge component renders a "badge" that can either take in a `Int` count or a `String` label. This is utilized in the [MessageList](#messagelist) component serving as the date separators, in the [ChatList](#chatlist) component to show unread counts, and in the [ChannelSettingsView](views.md#channelsettingsview) for displaying admins.

There are a few overload methods for this component dependent on your usage and level of customization needs.

```kotlin
Badge(count = 1) // renders a Badge with the number 1
Badge(count = 101) // renders a Badge with 99+
Badge(label = "Admin")
Badge(label = "Power User", backgroundColor = Color.Green)
```

For a more detailed API definition, check out the API docs here.

## ChannelGroup

This component renders a given list of `Chat` channels in a `Column`, each chat in its own ChannelRow, with the `Chat.displayName` as the title and the subtitle showing a preview of the members with in it.
This is utilized in the [UserDetailsView](views.md#userdetailsview) to show channels the current user has in common with any other user.

```kotlin
ChannelGroup(
    channels = listOf(yourChannels),
)
```

For a more detailed API definition, check out the API docs here.

## ChannelRow

Renders a description of a `Chat` channel in a row format.

This is utilized in the [ChannelGroup](#channelgroup) within [UserDetailsView](views.md#userdetailsview) to show channels the current user has in common with any other user.

There are a few overload methods for this component dependent on your usage and level of customization needs.

```kotlin
ChannelRow(chat = channel)
CHannelRow(chat = channel, showMemberPreview = true)
ChannelRow(
    imageUrls = listOf(userImages),
    title = "My Favorite Channel",
    onClick = { }
)
```

For a more detailed API definition, check out the API docs here.

## ChatInput

Text input that handles the sending of messages to a given Chat when the send button is pressed. 
This is generally used for [MessageList](#messagelist) as there is handling for an attachment sheet that will present from the callback `onMedia`.

```kotlin
ChatInput(chat = chat, onMedia = {})
```

For a more detailed API definition, check out the API docs here.

## ChatList

Renders a given list of `Chat` in an infinite scrolling list. Each chat will render a preview of it using [ChatMessagePreview](#chatmessagepreview).

```kotlin
ChatList(
    header = { Header() },
    onChatClicked = { }
)
```

For a more detailed API definition, check out the API docs here.

## ChatMessage

Renders the contents of a given Message from a `Chat`. 

This is used by [MessageList](#messagelist) to form the contents of a conversational chat, by properly aligning messages to left or right depending on sender (left aligned for incoming and right aligned for outgoing).

There are a few overload methods for this component dependent on your usage and level of customization needs.

```kotlin
ChatMessage(message = message, onLongPress = { })
ChatMessage(message = message, onLongPress = { }, onClick = { })
ChatMessage(message = message, onLongPress = { }, shape = RoundedCornerShape(20.dp))
ChatMessage(message = message, onLongPress = { }, showAvatar = true, showTimestamp = false)
```

For a more detailed API definition, check out the API docs here.

## ChatMessagePreview

Renders a "preview" for a given chat. This is based on the last message, if any, that was either sent or received in the chat. Attachments will be rendered as well.
This is utilized in [ChatList](#chatlist) to show previews for all chats that a user is currently a member of.

```kotlin
ChatMessagePreview(chat = chat, onClick = {})
```

For a more detailed API definition, check out the API docs here.

## Header

A top bar that can be utilized together with a content view to create a screen.

There are a few overload methods for this component dependent on your usage and level of customization needs.

```kotlin
Header()
Header(onBackClicked = { })
Header(title = "Create a Channel", onBackClicked = { })
Header(title = "Edit Profile", endAction = { HeaderDefaults.SaveAction { } })
```

For a more detailed API definition, check out the API docs here.

## MediaActionSheet

A modal bottom sheet that displays attachments that can be sent in a chat. This is a top level scaffold that is designed to wrap your screen content.

```kotlin
val mediaSheetState = rememberMediaActionSheetState(chat = chat)
MediaActionSheet(state) { 
    // your screen content
}
```

For a more detailed API definition, check out the API docs here.

## MessageActionSheet

A modal bottom sheet that allows contextual actions for a given messaged. This is a top level scaffold that is designed to wrap your screen content.
This can be utilized in conjunction with [MessageList](#messagelist) to show contextual actions for the `MessageList#onLongPress` callback.

```kotlin
val messageSheetState = rememberMessageActionSheetState()
MessageActionSheet(state) { 
    // your screen content
}
```

For a more detailed API definition, check out the API docs here.

## MessageList

A conversational component displaying the messages for a given [Chat] in an infinite scrolling list.

```kotlin
MessageList(chat = chat, header = { Header() }, onPressUser = {}, onLongPress = {})
```

For a more detailed API definition, check out the API docs here.

## Spinner

Themed spinner, colored with `ai.botstacks.sdk.ui.theme.Colors.primary`.

```kotlin
Spinner()
```

For a more detailed API definition, check out the API docs here.

## UserProfile

Renders an [Avatar](#avatar) and the display name for a given User in a centered Column.

```kotlin
UserProfile(user)
```

For a more detailed API definition, check out the API docs here.

## UserSelect

A component that renders User's in a horizontally scrolling Row. 

This is primarily used in [CreateChannelView](views.md#createchannelview) for showing currently selected Users and allowing the ability to add more if desired.

```kotlin
UserSelect(selectedUsers = userList, onAdd = {})
```

For a more detailed API definition, check out the API docs here.