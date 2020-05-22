# Template VIPER
asd
## Base Module
asdas
### Bottom Dialog
Untuk mengatur tampilan bottom dialog.

**Variable Name** `bottomMessageDialog`
**Methode Name** `showBottomDialogMessage`

**Attribute**
`dialogType` = [`MESSAGE_ONLY,
MESSAGE_WITH_BUTTON,
MESSAGE_WITH_CONFIRM`]
`title` = Title for dialog
`content` = Content for dialog
`img` = Image for dialog

**Event Listener**
 `override fun onBottomMessageYesClicked()` = button "yes" listener
 `override fun onBottomMessageNoClicked()` = button "no" listener

### Center Dialog
Untuk mengatur tampilan center dialog.

**Variable Name** `centerMessageDialog`
**Methode Name** `showCenterDialogMessage`

**Attribute**
`dialogType` = [`MESSAGE_ONLY,
MESSAGE_WITH_BUTTON,
MESSAGE_WITH_CONFIRM`]
`title` = Title for dialog
`content` = Content for dialog
`img` = Image for dialog

**Event Listener**
 `override fun onCenterMessageYesClicked()` = button "yes" listener
 `override fun onCenterMessageNoClicked()` = button "no" listener

### Full Dialog
Untuk mengatur tampilan Full dialog.

**Variable Name** `fullMessageDialog`
**Methode Name** `showFullDialogMessage`

**Attribute**
`dialogType` = [`MESSAGE_ONLY,
MESSAGE_WITH_BUTTON,
MESSAGE_WITH_CONFIRM`]
`title` = Title for dialog
`content` = Content for dialog
`img` = Image for dialog

**Event Listener**
 `override fun onFullMessageYesClicked()` = button "yes" listener
 `override fun onFullMessageNoClicked()` = button "no" listener
