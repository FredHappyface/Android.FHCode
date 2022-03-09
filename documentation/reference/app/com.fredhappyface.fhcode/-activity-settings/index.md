//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[ActivitySettings](index.md)

# ActivitySettings

[androidJvm]\
class [ActivitySettings](index.md) : [ActivityThemable](../-activity-themable/index.md)

ActivitySettings class inherits from the ActivityThemable class - provides the settings view

## Constructors

| | |
|---|---|
| [ActivitySettings](-activity-settings.md) | [androidJvm]<br>fun [ActivitySettings](-activity-settings.md)() |

## Functions

| Name | Summary |
|---|---|
| [changeTheme](change-theme.md) | [androidJvm]<br>fun [changeTheme](change-theme.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Compare view.id of the radio button selected to set the theme and recreate the activity |
| [onCreate](on-create.md) | [androidJvm]<br>protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Override the onCreate method from ActivityThemable adding the activity_settings view and selecting the current theme |
