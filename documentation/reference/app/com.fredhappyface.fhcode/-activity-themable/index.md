//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[ActivityThemable](index.md)

# ActivityThemable

[androidJvm]\
open class [ActivityThemable](index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)

ActivityThemable class inherits from the AppCompatActivity class - AppCompatActivity with custom themes. Overrides onCreate and onResume to set the theme

## Constructors

| | |
|---|---|
| [ActivityThemable](-activity-themable.md) | [androidJvm]<br>fun [ActivityThemable](-activity-themable.md)() |

## Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | [androidJvm]<br>protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Triggered when the activity is created. Sets the theme to one that the user selected |
| [onResume](on-resume.md) | [androidJvm]<br>protected open override fun [onResume](on-resume.md)()<br>Triggered when an activity is resumed. If the theme differs from the currently active theme, then the activity is recreated |

## Properties

| Name | Summary |
|---|---|
| [currentTheme](current-theme.md) | [androidJvm]<br>internal var [currentTheme](current-theme.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [sharedPreferences](shared-preferences.md) | [androidJvm]<br>internal lateinit var [sharedPreferences](shared-preferences.md): [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html) |

## Inheritors

| Name |
|---|
| [ActivityAbout](../-activity-about/index.md) |
| [ActivityMain](../-activity-main/index.md) |
| [ActivitySettings](../-activity-settings/index.md) |
